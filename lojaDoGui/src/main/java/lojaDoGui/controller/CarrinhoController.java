package lojaDoGui.controller;

import io.swagger.annotations.Api;
import lojaDoGui.dto.CarrinhoDTO;
import lojaDoGui.dto.CarrinhoFinalizadoDTO;
import lojaDoGui.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/carrinho")
@Api(value = "API Carrinho de compras", tags = { "Carrinho" })
public class CarrinhoController {

    @Autowired
    private CarrinhoService service;

    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> salvarCarrinho(@RequestBody @Valid CarrinhoDTO dto)
            throws Exception {

        CarrinhoFinalizadoDTO carrinho = service.salvar(dto);
        return new ResponseEntity<>(carrinho, HttpStatus.CREATED);
    }

}