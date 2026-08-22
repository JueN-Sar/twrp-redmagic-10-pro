package cn.nubia.gameassist.common;

import android.text.TextUtils;
import cn.nubia.gameassist.dessert.tiles.AFKTile;
import cn.nubia.gameassist.dessert.tiles.ActiveModeTile;
import cn.nubia.gameassist.dessert.tiles.BarrageMessageTiles;
import cn.nubia.gameassist.dessert.tiles.ChargeSeparationTiles;
import cn.nubia.gameassist.dessert.tiles.CleanTile;
import cn.nubia.gameassist.dessert.tiles.CompetitionLightTiles;
import cn.nubia.gameassist.dessert.tiles.CustomTile;
import cn.nubia.gameassist.dessert.tiles.DockTile;
import cn.nubia.gameassist.dessert.tiles.FanTile;
import cn.nubia.gameassist.dessert.tiles.GameBenefit;
import cn.nubia.gameassist.dessert.tiles.GameHandlerTile;
import cn.nubia.gameassist.dessert.tiles.GameNoNotiTile;
import cn.nubia.gameassist.dessert.tiles.GameNoTelTile;
import cn.nubia.gameassist.dessert.tiles.GameReminderTiles;
import cn.nubia.gameassist.dessert.tiles.GameThirdAppTitle;
import cn.nubia.gameassist.dessert.tiles.ImageSearchTile;
import cn.nubia.gameassist.dessert.tiles.LinkMicsTranslationTile;
import cn.nubia.gameassist.dessert.tiles.LiquidCoolingTile;
import cn.nubia.gameassist.dessert.tiles.LowSugarGameplayTile;
import cn.nubia.gameassist.dessert.tiles.MagicVoiceTile;
import cn.nubia.gameassist.dessert.tiles.ManualRecordTile;
import cn.nubia.gameassist.dessert.tiles.MisOperateTile;
import cn.nubia.gameassist.dessert.tiles.MultiSubScreenTile;
import cn.nubia.gameassist.dessert.tiles.PerformanceMonitorTile;
import cn.nubia.gameassist.dessert.tiles.QuitTile;
import cn.nubia.gameassist.dessert.tiles.RecordTile;
import cn.nubia.gameassist.dessert.tiles.RefreshRateTile;
import cn.nubia.gameassist.dessert.tiles.RotationLockTile;
import cn.nubia.gameassist.dessert.tiles.SmallWindowTile;
import cn.nubia.gameassist.dessert.tiles.SuperSnapTile;
import cn.nubia.gameassist.dessert.tiles.VirtualHandleTile;
import cn.nubia.gameassist.dessert.tiles.WifiDisplayTile;
import cn.nubia.gameassist.dessert.tiles.WifiTile;
import cn.nubia.gameassist.plugin.tiles.AITipTile;
import cn.nubia.gameassist.plugin.tiles.AITriggerTile;
import cn.nubia.gameassist.plugin.tiles.AiDetectTile;
import cn.nubia.gameassist.plugin.tiles.BiabloTile;
import cn.nubia.gameassist.plugin.tiles.CardAssistTile;
import cn.nubia.gameassist.plugin.tiles.ChatAssistTile;
import cn.nubia.gameassist.plugin.tiles.CombatPowerTile;
import cn.nubia.gameassist.plugin.tiles.CounterTile;
import cn.nubia.gameassist.plugin.tiles.CustomeSortTile;
import cn.nubia.gameassist.plugin.tiles.GamePredictionTile;
import cn.nubia.gameassist.plugin.tiles.GameShaderTile;
import cn.nubia.gameassist.plugin.tiles.HighSensitivityWheelTile;
import cn.nubia.gameassist.plugin.tiles.HuntingModeTile;
import cn.nubia.gameassist.plugin.tiles.InvestigateModeTile;
import cn.nubia.gameassist.plugin.tiles.KeyPositionAssistTile;
import cn.nubia.gameassist.plugin.tiles.MagicElvesAidTile;
import cn.nubia.gameassist.plugin.tiles.MoraAiSpeakerTile;
import cn.nubia.gameassist.plugin.tiles.OneKeyLinkTile;
import cn.nubia.gameassist.plugin.tiles.OperationDevicesTile;
import cn.nubia.gameassist.plugin.tiles.PleasedDisplayTile;
import cn.nubia.gameassist.plugin.tiles.RangeLineTile;
import cn.nubia.gameassist.plugin.tiles.RedMagicBroadcastTile;
import cn.nubia.gameassist.plugin.tiles.ScreenExtractionTile;
import cn.nubia.gameassist.plugin.tiles.SensorOperationTile;
import cn.nubia.gameassist.plugin.tiles.SightAssistTile;
import cn.nubia.gameassist.plugin.tiles.SoundEffectTile;
import cn.nubia.gameassist.plugin.tiles.SuperResolutionOldTile;
import cn.nubia.gameassist.plugin.tiles.SuperResolutionTile;
import cn.nubia.gameassist.plugin.tiles.TimerTile;
import cn.nubia.gameassist.plugin.tiles.VibrateTile;
import cn.nubia.gameassist.plugin.tiles.VoiceControllerTile;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;

/* loaded from: classes.dex */
public class TileFactory {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static QSTile a(String str, TileHost tileHost) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -2104338710:
                if (str.equals("manual_record")) {
                    c2 = 0;
                    break;
                }
                break;
            case -2068867766:
                if (str.equals("game_benefit")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1914898913:
                if (str.equals("game_reminder")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1847875055:
                if (str.equals("multi_sub_screen")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1815319114:
                if (str.equals("range_line")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1758770008:
                if (str.equals("barrage_message")) {
                    c2 = 5;
                    break;
                }
                break;
            case -1710053469:
                if (str.equals("link_mics_translation")) {
                    c2 = 6;
                    break;
                }
                break;
            case -1695216677:
                if (str.equals("game_browser")) {
                    c2 = 7;
                    break;
                }
                break;
            case -1636233215:
                if (str.equals("rotaton_lock")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -1625066512:
                if (str.equals("super_resolution")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -1468382430:
                if (str.equals("sensor_operation")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -1433756010:
                if (str.equals("competition_light")) {
                    c2 = 11;
                    break;
                }
                break;
            case -1418024956:
                if (str.equals("ai_tip")) {
                    c2 = '\f';
                    break;
                }
                break;
            case -1387765599:
                if (str.equals("ai_trigger")) {
                    c2 = '\r';
                    break;
                }
                break;
            case -1386415557:
                if (str.equals("refreshrate")) {
                    c2 = 14;
                    break;
                }
                break;
            case -1333657659:
                if (str.equals("chat_assit")) {
                    c2 = 15;
                    break;
                }
                break;
            case -1224577496:
                if (str.equals("handle")) {
                    c2 = 16;
                    break;
                }
                break;
            case -1214981800:
                if (str.equals("super_resolution_old")) {
                    c2 = 17;
                    break;
                }
                break;
            case -1137258691:
                if (str.equals("pleased_display")) {
                    c2 = 18;
                    break;
                }
                break;
            case -1051531460:
                if (str.equals("active_mode")) {
                    c2 = 19;
                    break;
                }
                break;
            case -934908847:
                if (str.equals("record")) {
                    c2 = 20;
                    break;
                }
                break;
            case -814741799:
                if (str.equals("keylink")) {
                    c2 = 21;
                    break;
                }
                break;
            case -744138276:
                if (str.equals("virtual_handle")) {
                    c2 = 22;
                    break;
                }
                break;
            case -684817269:
                if (str.equals("low_sugar")) {
                    c2 = 23;
                    break;
                }
                break;
            case -649689348:
                if (str.equals("game_prediction")) {
                    c2 = 24;
                    break;
                }
                break;
            case -509027403:
                if (str.equals("game_bilibili")) {
                    c2 = 25;
                    break;
                }
                break;
            case -438810530:
                if (str.equals("game_custom")) {
                    c2 = 26;
                    break;
                }
                break;
            case -415658303:
                if (str.equals("game_douyin")) {
                    c2 = 27;
                    break;
                }
                break;
            case -257909062:
                if (str.equals("game_kuaishou")) {
                    c2 = 28;
                    break;
                }
                break;
            case -195606131:
                if (str.equals("game_qq")) {
                    c2 = 29;
                    break;
                }
                break;
            case 96486:
                if (str.equals("afk")) {
                    c2 = 30;
                    break;
                }
                break;
            case 101139:
                if (str.equals("fan")) {
                    c2 = 31;
                    break;
                }
                break;
            case 114715:
                if (str.equals("tel")) {
                    c2 = ' ';
                    break;
                }
                break;
            case 3088947:
                if (str.equals("dock")) {
                    c2 = '!';
                    break;
                }
                break;
            case 3198785:
                if (str.equals("help")) {
                    c2 = '\"';
                    break;
                }
                break;
            case 3387382:
                if (str.equals("noti")) {
                    c2 = '#';
                    break;
                }
                break;
            case 3482191:
                if (str.equals("quit")) {
                    c2 = '$';
                    break;
                }
                break;
            case 3534794:
                if (str.equals("snap")) {
                    c2 = '%';
                    break;
                }
                break;
            case 3649301:
                if (str.equals("wifi")) {
                    c2 = '&';
                    break;
                }
                break;
            case 94746185:
                if (str.equals("clean")) {
                    c2 = NubiaTextClock.QUOTE;
                    break;
                }
                break;
            case 110364485:
                if (str.equals("timer")) {
                    c2 = '(';
                    break;
                }
                break;
            case 112386354:
                if (str.equals("voice")) {
                    c2 = ')';
                    break;
                }
                break;
            case 118507539:
                if (str.equals("game_wechat")) {
                    c2 = '*';
                    break;
                }
                break;
            case 131790998:
                if (str.equals("mora_ai_speaker")) {
                    c2 = '+';
                    break;
                }
                break;
            case 159378944:
                if (str.equals("keyposition_assist")) {
                    c2 = ',';
                    break;
                }
                break;
            case 204763768:
                if (str.equals("card_assist")) {
                    c2 = '-';
                    break;
                }
                break;
            case 451310959:
                if (str.equals("vibrate")) {
                    c2 = '.';
                    break;
                }
                break;
            case 455318170:
                if (str.equals("ai_detect")) {
                    c2 = '/';
                    break;
                }
                break;
            case 516441163:
                if (str.equals("performance_monitor")) {
                    c2 = '0';
                    break;
                }
                break;
            case 713478661:
                if (str.equals("operation_devices")) {
                    c2 = '1';
                    break;
                }
                break;
            case 735743244:
                if (str.equals("image_search")) {
                    c2 = '2';
                    break;
                }
                break;
            case 883726313:
                if (str.equals("custome_sort")) {
                    c2 = '3';
                    break;
                }
                break;
            case 935718711:
                if (str.equals("biablo_mode")) {
                    c2 = '4';
                    break;
                }
                break;
            case 945397169:
                if (str.equals("charge_separation")) {
                    c2 = '5';
                    break;
                }
                break;
            case 957830652:
                if (str.equals("counter")) {
                    c2 = '6';
                    break;
                }
                break;
            case 1012588072:
                if (str.equals("small_window")) {
                    c2 = '7';
                    break;
                }
                break;
            case 1073054921:
                if (str.equals("voice_controller")) {
                    c2 = '8';
                    break;
                }
                break;
            case 1182494401:
                if (str.equals("sound_effect")) {
                    c2 = '9';
                    break;
                }
                break;
            case 1244924926:
                if (str.equals("redmagic_broadcast")) {
                    c2 = ':';
                    break;
                }
                break;
            case 1265886202:
                if (str.equals("combat_power")) {
                    c2 = ';';
                    break;
                }
                break;
            case 1362469996:
                if (str.equals("liquid_cool")) {
                    c2 = '<';
                    break;
                }
                break;
            case 1475800158:
                if (str.equals("high_sensitivity_wheel")) {
                    c2 = '=';
                    break;
                }
                break;
            case 1521082560:
                if (str.equals("investigation_mode")) {
                    c2 = '>';
                    break;
                }
                break;
            case 1532266071:
                if (str.equals("gameshader")) {
                    c2 = '?';
                    break;
                }
                break;
            case 1589362803:
                if (str.equals("hunting_mode")) {
                    c2 = '@';
                    break;
                }
                break;
            case 1803357003:
                if (str.equals("sight_assist")) {
                    c2 = 'A';
                    break;
                }
                break;
            case 1839085978:
                if (str.equals("screen_extraction")) {
                    c2 = 'B';
                    break;
                }
                break;
            case 1934780818:
                if (str.equals("whatsapp")) {
                    c2 = 'C';
                    break;
                }
                break;
            case 1938034588:
                if (str.equals("mis_operate")) {
                    c2 = 'D';
                    break;
                }
                break;
            case 2058565645:
                if (str.equals("wifidisplay")) {
                    c2 = 'E';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return new ManualRecordTile(tileHost);
            case 1:
                return new GameBenefit(tileHost);
            case 2:
                return new GameReminderTiles(tileHost);
            case 3:
                return new MultiSubScreenTile(tileHost);
            case 4:
                return new RangeLineTile(tileHost);
            case 5:
                return new BarrageMessageTiles(tileHost);
            case 6:
                return new LinkMicsTranslationTile(tileHost);
            case 7:
            case 25:
            case 27:
            case 28:
            case 29:
            case '*':
            case 'C':
                return new GameThirdAppTitle(tileHost);
            case '\b':
                return new RotationLockTile(tileHost);
            case '\t':
                return new SuperResolutionTile(tileHost);
            case '\n':
                return new SensorOperationTile(tileHost);
            case 11:
                return new CompetitionLightTiles(tileHost);
            case '\f':
                return new AITipTile(tileHost);
            case '\r':
                return new AITriggerTile(tileHost);
            case 14:
                return new RefreshRateTile(tileHost);
            case 15:
                return new ChatAssistTile(tileHost);
            case 16:
                return new GameHandlerTile(tileHost);
            case MlKitException.NETWORK_ISSUE /* 17 */:
                return new SuperResolutionOldTile(tileHost);
            case MlKitException.UNSUPPORTED /* 18 */:
                return new PleasedDisplayTile(tileHost);
            case 19:
                return new ActiveModeTile(tileHost);
            case 20:
                return new RecordTile(tileHost);
            case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                return new OneKeyLinkTile(tileHost);
            case 22:
                return new VirtualHandleTile(tileHost);
            case 23:
                return new LowSugarGameplayTile(tileHost);
            case 24:
                return new GamePredictionTile(tileHost);
            case 26:
                return new CustomTile(tileHost);
            case 30:
                return new AFKTile(tileHost);
            case 31:
                return new FanTile(tileHost);
            case ' ':
                return new GameNoTelTile(tileHost);
            case '!':
                return new DockTile(tileHost);
            case '\"':
                return new MagicElvesAidTile(tileHost);
            case '#':
                return new GameNoNotiTile(tileHost);
            case '$':
                return new QuitTile(tileHost);
            case '%':
                return new SuperSnapTile(tileHost);
            case '&':
                return new WifiTile(tileHost);
            case '\'':
                return new CleanTile(tileHost);
            case '(':
                return new TimerTile(tileHost);
            case ')':
                return new MagicVoiceTile(tileHost);
            case '+':
                return new MoraAiSpeakerTile(tileHost);
            case ',':
                return new KeyPositionAssistTile(tileHost);
            case '-':
                return new CardAssistTile(tileHost);
            case '.':
                return new VibrateTile(tileHost);
            case '/':
                return new AiDetectTile(tileHost);
            case '0':
                return new PerformanceMonitorTile(tileHost);
            case '1':
                return new OperationDevicesTile(tileHost);
            case '2':
                return new ImageSearchTile(tileHost);
            case '3':
                return new CustomeSortTile(tileHost);
            case '4':
                return new BiabloTile(tileHost);
            case '5':
                return new ChargeSeparationTiles(tileHost);
            case '6':
                return new CounterTile(tileHost);
            case '7':
                return new SmallWindowTile(tileHost);
            case '8':
                return new VoiceControllerTile(tileHost);
            case '9':
                return new SoundEffectTile(tileHost);
            case ':':
                return new RedMagicBroadcastTile(tileHost);
            case ';':
                return new CombatPowerTile(tileHost);
            case '<':
                return new LiquidCoolingTile(tileHost);
            case '=':
                return new HighSensitivityWheelTile(tileHost);
            case '>':
                return new InvestigateModeTile(tileHost);
            case '?':
                return new GameShaderTile(tileHost);
            case '@':
                return new HuntingModeTile(tileHost);
            case 'A':
                return new SightAssistTile(tileHost);
            case 'B':
                return new ScreenExtractionTile(tileHost);
            case 'D':
                return new MisOperateTile(tileHost);
            case 'E':
                return new WifiDisplayTile(tileHost);
            default:
                throw new IllegalArgumentException("Bad tile spec: " + str);
        }
    }
}
