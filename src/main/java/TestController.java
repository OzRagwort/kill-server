import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test/{maxThreads}")
    public void setMaxThreads(@PathVariable final Long maxThreads) throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime()
                .exec("sh /home/ubuntu/run.sh " + maxThreads);

        if (!process.waitFor(15, TimeUnit.SECONDS)) {
            throw new RuntimeException("timeout!!");
        }
        final InputStream inputStream = process.getInputStream();
        final String result = String.valueOf(inputStream.readAllBytes());
        System.out.println(result);
    }
}
