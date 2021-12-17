package lojaDoGui.service;

import lojaDoGui.domain.Cupom;
import lojaDoGui.dto.CupomDTO;
import lojaDoGui.exception.ApiException;
import lojaDoGui.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CupomService {

    @Autowired
    private CupomRepository repository;

    public List<CupomDTO> listar(){
        List<CupomDTO> lista = new ArrayList<CupomDTO>();
        List<Cupom> cupons = repository.findAll();
        for (Cupom cupom : cupons) {
            lista.add(new CupomDTO(cupom));
        }
        return lista;
    }

    public CupomDTO buscarPorCodigo(String codigo) throws ApiException {
        Optional<Cupom> opt = repository.findByCodigo(codigo);
        if(!opt.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cupom não encontrado!");
        }

        return new CupomDTO(opt.get());
    }

    public CupomDTO salvar(CupomDTO dto) {
        Cupom cupom = repository.save(dto.toCupom());
        return new CupomDTO(cupom);
    }

    public CupomDTO alterar(CupomDTO dto) throws ApiException {
        Optional<Cupom> opt = repository.findById(dto.getId());
        if(!opt.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cupom não encontrado!");
        }

        return salvar(dto);
    }

    public void excluir(String codigo) throws ApiException {
        Optional<Cupom> opt = repository.findByCodigo(codigo);
        if(!opt.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Cupom não encontrado!");
        }

        repository.delete(opt.get());
    }

}
