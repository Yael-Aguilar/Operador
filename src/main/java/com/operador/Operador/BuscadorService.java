package com.operador.Operador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BuscadorService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${getBuscador.url}")
    private String getBuscadorUrl;

    @GetMapping("/getOrdenes")
    public List<Orden> getOrders() {
        String url = getBuscadorUrl + "/todasLasOrdenes";
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Orden> orders = null;
        try {
            orders = objectMapper.readValue(response, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    @PostMapping("/crearOrden")
    public String createOrden(@RequestBody Orden Orden) {
        String url = getBuscadorUrl + "/orden";
        return restTemplate.postForObject(url, Orden, String.class);
    }

    @DeleteMapping("/deleteOrden")
    public ResponseEntity<String> deleteOrden(@RequestParam String id) {
        String url = getBuscadorUrl + "/eliminarOrden?id=" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("Order deleted successfully");
    }

}