
import static junit.framework.Assert.fail;
import model.bean.Categoria;
import model.bean.Produto;
import model.dao.CategoriaDAO;
import model.dao.ProdutoDAO;
import org.junit.Ignore;
import org.junit.Test;

public class ProdutoDAOTest {

    public ProdutoDAOTest() {

    }

    @Test
    @Ignore
    public void inserir() {
        Categoria cat = new Categoria();
        cat.setId(4);
        Produto prod = new Produto("Moleton", 4, 102.80, cat);
        ProdutoDAO dao = new ProdutoDAO();
        if (dao.save(prod)) {
            System.out.println("Salvo com sucesso");
        } else {
            fail("Erro ao salvar");
        }
    }
    
    @Test  
    public void listar(){
        ProdutoDAO dao = new ProdutoDAO();
        for(Produto ca: dao.findAll()){
            System.out.println("Produto: " + ca.getDescricao());
        }
    }
    
    @Test
    @Ignore
    public void editar(){
        Categoria cat = new Categoria();
        cat.setId(3);
        Produto prod = new Produto("Belgravia", 2, 22.80, cat);
        prod.setId(2);
        ProdutoDAO dao = new ProdutoDAO();
        if (dao.update(prod)) {
            System.out.println("Editado com sucesso");
        } else {
            fail("Erro ao editar");
        }
    }
    
    @Test
    @Ignore
    public void excluir(){        
        Produto prod = new Produto();
        prod.setId(2);
        ProdutoDAO dao = new ProdutoDAO();
        if (dao.delete(prod)) {
            System.out.println("Excluído com sucesso");
        } else {
            fail("Erro ao excluir");
        }
    }
}
