package com.android.systemui.shared.recents.model;

import android.util.ArrayMap;
import android.util.SparseArray;
import com.android.systemui.shared.recents.model.Task;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
class FilteredTaskList {
    private TaskFilter mFilter;
    private final ArrayList<Task> mTasks = new ArrayList<>();
    private final ArrayList<Task> mFilteredTasks = new ArrayList<>();
    private final ArrayMap<Task.TaskKey, Integer> mFilteredTaskIndices = new ArrayMap<>();

    FilteredTaskList() {
    }

    private void updateFilteredTaskIndices() {
        int size = this.mFilteredTasks.size();
        this.mFilteredTaskIndices.clear();
        for (int i = 0; i < size; i++) {
            this.mFilteredTaskIndices.put(this.mFilteredTasks.get(i).key, Integer.valueOf(i));
        }
    }

    private void updateFilteredTasks() {
        this.mFilteredTasks.clear();
        if (this.mFilter != null) {
            SparseArray<Task> sparseArray = new SparseArray<>();
            int size = this.mTasks.size();
            for (int i = 0; i < size; i++) {
                Task task = this.mTasks.get(i);
                sparseArray.put(task.key.id, task);
            }
            for (int i2 = 0; i2 < size; i2++) {
                Task task2 = this.mTasks.get(i2);
                if (this.mFilter.acceptTask(sparseArray, task2, i2)) {
                    this.mFilteredTasks.add(task2);
                }
            }
        } else {
            this.mFilteredTasks.addAll(this.mTasks);
        }
        updateFilteredTaskIndices();
    }

    void add(Task task) {
        this.mTasks.add(task);
        updateFilteredTasks();
    }

    boolean contains(Task task) {
        return this.mFilteredTaskIndices.containsKey(task.key);
    }

    ArrayList<Task> getTasks() {
        return this.mFilteredTasks;
    }

    int indexOf(Task task) {
        if (task == null || !this.mFilteredTaskIndices.containsKey(task.key)) {
            return -1;
        }
        return this.mFilteredTaskIndices.get(task.key).intValue();
    }

    boolean remove(Task task) {
        if (!this.mFilteredTasks.contains(task)) {
            return false;
        }
        boolean remove = this.mTasks.remove(task);
        updateFilteredTasks();
        return remove;
    }

    void set(List<Task> list) {
        this.mTasks.clear();
        this.mTasks.addAll(list);
        updateFilteredTasks();
    }

    boolean setFilter(TaskFilter taskFilter) {
        ArrayList arrayList = new ArrayList(this.mFilteredTasks);
        this.mFilter = taskFilter;
        updateFilteredTasks();
        return !arrayList.equals(this.mFilteredTasks);
    }

    int size() {
        return this.mFilteredTasks.size();
    }
}
