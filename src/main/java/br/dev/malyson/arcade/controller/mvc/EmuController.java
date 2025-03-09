package br.dev.malyson.arcade.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/emu")
public class EmuController {

    @PostMapping("/save-state")
    @ResponseBody
    public String saveState(@RequestBody Map<String, Object> dto) {
        String name = (String) dto.get("name");
        String data = (String) dto.get("data");

        // Decodificar os dados base64
        byte[] decodedBytes = Base64.getDecoder().decode(data);

        // Salvar os dados em um arquivo
        try (FileOutputStream fos = new FileOutputStream("/home/malys/Documentos/repo/Arcade/src/main/resources/static/js/load.state")) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"success\": false}";
        }

        return "{\"success\": true}";
    }

    @PostMapping("/load-state")
    @ResponseBody
    public String loadState() {
        String filePath = "/home/malys/Documentos/repo/Arcade/src/main/resources/static/js/load.state";

        try {
            // Ler todo o conteúdo do arquivo
            byte[] data = Files.readAllBytes(Paths.get(filePath));

            // Converter para Base64
            String encodedString = Base64.getEncoder().encodeToString(data);

            // Criar um JSON corretamente formatado
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", encodedString);

            // Usar Jackson para converter o Map para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);

        } catch (IOException e) {
            e.printStackTrace();

            // Retornar JSON corretamente formatado em caso de erro
            return "{\"success\": false}";
        }
    }

}
