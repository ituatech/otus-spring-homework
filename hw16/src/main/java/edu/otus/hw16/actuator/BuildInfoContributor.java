package edu.otus.hw16.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik Bespalov on 17/10/2018.
 *
 * @author Nik Bespalov.
 */
@Component
public class BuildInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String,String> data= new HashMap<>();
        data.put("app", "Book Library");
        data.put("build.version", "1.0.0");
        builder.withDetail("AppInfo", data);
    }

}
