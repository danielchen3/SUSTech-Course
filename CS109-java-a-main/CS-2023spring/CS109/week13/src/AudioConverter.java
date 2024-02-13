import javax.sound.sampled.*;
import java.io.File;

public class AudioConverter {
    public static void convertToWav(String inputPath, String outputPath) {
        try {
            // 读取输入音频文件
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(inputPath));

            // 获取输入音频文件的格式
            AudioFormat format = audioInputStream.getFormat();

            // 创建输出音频流格式（使用WAV格式）
            AudioFormat outputFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    format.getSampleRate(),
                    16,
                    format.getChannels(),
                    format.getChannels() * 2,
                    format.getSampleRate(),
                    false
            );

            // 打开输出音频流
            AudioInputStream convertedAudioInputStream = AudioSystem.getAudioInputStream(outputFormat, audioInputStream);

            // 将转换后的音频流写入输出文件
            AudioSystem.write(convertedAudioInputStream, AudioFileFormat.Type.WAVE, new File(outputPath));

            // 关闭流
            audioInputStream.close();
            convertedAudioInputStream.close();

            System.out.println("音频转换完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputPath = "C:\\Users\\B_W_Y_Y\\Desktop\\大一春\\java a\\project\\CS109-2023-Sping-ChessDemo\\resource\\Music\\ddd.mp3"; // 输入音频文件路径
        String outputPath = "C:\\Users\\B_W_Y_Y\\Desktop\\大一春\\java a\\project\\CS109-2023-Sping-ChessDemo\\resource\\Musi\\lll.wav"; // 输出音频文件路径

        convertToWav(inputPath, outputPath);
    }
}
