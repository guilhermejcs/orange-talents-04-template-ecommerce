package br.com.zupacademy.guilhermejcs.mercadolivre.fechamentocompra;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.UsuarioRepository;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class FechaCompraParte1Controller {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public FechaCompraParte1Controller() {
    }

    public FechaCompraParte1Controller(EntityManager manager, UsuarioRepository usuarioRepository) {
        this.manager = manager;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(value = "/compras")
    @Transactional
    public String compra(@RequestBody @Valid NovaCompraRequest request,
                         UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());

        int quantidade = request.getQuantidade();
        boolean abateu = produtoASerComprado.abateEstoque(quantidade);
        if (abateu) {
            Usuario comprador = usuarioRepository.findByEmail("guilhrme2@deveficiente.com").get();
            GatewayPagamento gateway = request.getGateway();

            Compra novaCompra = new Compra(produtoASerComprado, quantidade, comprador, request.getGateway());
            manager.persist(novaCompra);

            if(gateway.equals(GatewayPagamento.pagseguro)){
                String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
                return "pagseguro.com/" + novaCompra.getId()
                        + "?redirectUrl="+urlRetornoPagseguro;
            }else{
                String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
                return "paypal.com/" + novaCompra.getId()
                        + "?redirectUrl="+urlRetornoPaypal;
            }
        }

        BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
        problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");
       throw problemaComEstoque;
    }
}
