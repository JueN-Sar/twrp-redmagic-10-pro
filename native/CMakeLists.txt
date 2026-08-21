cmake_minimum_required(VERSION 3.18)
project(GameSpaceUnleashed)

# Set C++ standard
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Build as shared library for Zygisk
add_library(${PROJECT_NAME} SHARED
    src/main.cpp
)

# LSPlant - added as subdirectory (cloned during CI)
if(EXISTS "${CMAKE_CURRENT_SOURCE_DIR}/../external/lsplant/lsplant/src/main/jni/CMakeLists.txt")
    # Build LSPlant from source
    set(LSPLANT_DIR "${CMAKE_CURRENT_SOURCE_DIR}/../external/lsplant/lsplant/src/main/jni")
    add_subdirectory(${LSPLANT_DIR} lsplant_build)
    target_link_libraries(${PROJECT_NAME} lsplant)
else()
    message(FATAL_ERROR "LSPlant not found. Run: git clone https://github.com/LSPosed/LSPlant.git external/lsplant")
endif()

# Include paths
target_include_directories(${PROJECT_NAME} PRIVATE
    src
)

# Link Android log library
target_link_libraries(${PROJECT_NAME} log)

# Strip the output for release builds
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -s")

# Set output name to match Zygisk conventions
set_target_properties(${PROJECT_NAME} PROPERTIES
    OUTPUT_NAME "game_space_unleashed"
    LIBRARY_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/lib/${ANDROID_ABI}"
)
