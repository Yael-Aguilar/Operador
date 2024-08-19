package com.operador.Operador;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orden {
    private String id;
    private String fecha;
    private List<String> productos;
}
