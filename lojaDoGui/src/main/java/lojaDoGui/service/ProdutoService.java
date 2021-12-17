package lojaDoGui.service;


import lojaDoGui.domain.Produto;
import lojaDoGui.dto.ProdutoDTO;
import lojaDoGui.exception.ApiException;
import lojaDoGui.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = repository.save(dto.toProduto());
        return new ProdutoDTO(produto);
    }

    public List<ProdutoDTO> listar() {
        List<Produto> produtos = repository.findAll();
        List<ProdutoDTO> lista = new ArrayList<>();
        for (Produto produto : produtos) {
            lista.add(new ProdutoDTO(produto));
        }
        return lista;
    }

    public ProdutoDTO buscarPorId(Long id) throws ApiException {
        Optional<Produto> opt = repository.findById(id);
        if(!opt.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Produto não encontrado!");
        }

        return new ProdutoDTO(opt.get());
    }

    public ProdutoDTO alterar(ProdutoDTO dto) throws ApiException {
        Optional<Produto> opt = repository.findById(dto.getId());
        if(!opt.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Produto não encontrado!");
        }
        return salvar(dto);
    }

    public void excluir(Long id) throws ApiException {
        Optional<Produto> opt = repository.findById(id);
        if(!opt.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Produto não encontrado!");
        }
        repository.delete(opt.get());
    }

}
