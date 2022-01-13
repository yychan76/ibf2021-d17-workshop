package ibf.ssf.fortunecookieapi.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf.ssf.fortunecookieapi.service.FortuneCookie;
import jakarta.json.Json;
import jakarta.json.JsonObject;


@RestController
@RequestMapping(path="/cookies",
    produces=MediaType.APPLICATION_JSON_VALUE)
public class CookiesController {
    private final Logger logger = Logger.getLogger(CookiesController.class.getName());

    @Autowired
    private FortuneCookie fortune;

    @GetMapping
    public ResponseEntity<String> getCookies(@RequestParam(defaultValue = "1") Integer count) {
        logger.info("Requested cookies count: %s".formatted(count));

        if ((count < 1) || (count > 10)) {
            JsonObject errorJson = Json.createObjectBuilder().add("error", "Count must between 1 and 10").build();
            return ResponseEntity.badRequest().body(errorJson.toString());
        }
        List<String> cookies = fortune.getCookies(count);
        logger.info(Arrays.toString(cookies.toArray()));

        JsonObject cookiesJson = Json.createObjectBuilder().add("cookies", Json.createArrayBuilder(cookies))
            .add("timestamp", (new Date()).getTime()).build();
        return ResponseEntity.ok().body(cookiesJson.toString());
    }
}
