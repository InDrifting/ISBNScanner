import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

object Scanner {
    val reader = MultiFormatReader()
    fun Scan(fileName: String): String {
        try {
            val file = File(fileName)
            val image = ImageIO.read(file)
            val pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth())
            val source = RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels)
            val binarizer = HybridBinarizer(source)
            val bitmap = BinaryBitmap(binarizer)
            val ret = reader.decode(bitmap)
            println("Scanner: ${fileName} -> ${ret}")
            return ret.text
        } catch (e: NotFoundException) {
            println("Scanner: No result for ${fileName}")
            return ""
        } catch (e: IOException) {
            println("Scanner: Open ${fileName} error")
            return ""
        } catch (e: Exception) {
            println("Scanner: Error")
            e.printStackTrace()
            return ""
        }
    }
}