package com.example.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private static final Logger log = Logger.getLogger(jdbcClientRunRepository.class.getName());
    private final jdbcClientRunRepository repository;
    private final ObjectMapper objectMapper ;

    public RunJsonDataLoader(jdbcClientRunRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0){
            try (InputStream is = TypeReference.class.getResourceAsStream("/data/runs.json")) {
              Runs allRuns = objectMapper.readValue(is, Runs.class);
                log.info("Loaded "+allRuns.runs().size()+" runs from JSON file");
                repository.saveAll(allRuns.runs());
            }catch (IOException e){
                log.severe("Failed to load runs from JSON file: "+e.getMessage());
            }

        }
        else{
            log.info("Not loading runs from JSON file as there are already runs in the database");
        }
    }
}
