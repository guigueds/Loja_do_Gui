package lojaDoGui.service;

import lojaDoGui.domain.Carrinho;
import lojaDoGui.domain.Cupom;
import lojaDoGui.domain.ItemCarrinho;
import lojaDoGui.dto.CarrinhoDTO;
import lojaDoGui.dto.CarrinhoFinalizadoDTO;
import lojaDoGui.exception.ApiException;
import lojaDoGui.repository.CarrinhoRepository;
import lojaDoGui.repository.CupomRepository;
import lojaDoGui.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository repository;
    @Autowired private CupomRepository cupomRepository;
    @Autowired private ProdutoService produtoService;
    @Autowired private ItemCarrinhoRepository itemRepository;

    public CarrinhoFinalizadoDTO salvar(CarrinhoDTO dto) throws ApiException {
        Cupom cupomValido = getCupomMaiorValor(dto.getCupons());
        Carrinho carrinho = dto.toCarrinho(cupomValido, produtoService);
        repository.save(carrinho);
        for (ItemCarrinho item : carrinho.getItens()) {
            item.setCarrinho(carrinho);
            itemRepository.save(item);
        }
        return new CarrinhoFinalizadoDTO(carrinho);
    }

    private Cupom getCupomMaiorValor(List<String> cupons) {
        String cupom = "";
        Double desconto = 0.0;
        if(cupons.size() > 0) {
            for (String codigo : cupons) {
                Optional<Cupom> c1 = cupomRepository.findByCodigo(codigo);
                if(c1.isPresent()) {
                    if(desconto < c1.get().getPercentual()) {
                        cupom = codigo;
                        desconto += c1.get().getPercentual();
                    }
                }
            }
            return cupomRepository.findByCodigo(cupom).get();
        }
        return null;
    }
}

