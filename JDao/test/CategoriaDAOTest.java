
import static junit.framework.Assert.fail;
import model.bean.Categoria;
import model.dao.CategoriaDAO;
import org.junit.Ignore;
import org.junit.Test;

public class CategoriaDAOTest {

    public CategoriaDAOTest() {

    }

    @Test
    @Ignore  
    public void inserir() {
        Categoria cat = new Categoria("Alimentos");
        CategoriaDAO dao = new CategoriaDAO();
        if (dao.save(cat)) {
            System.out.println("Salvo com sucesso");
        } else {
            fail("Erro ao salvar");
        }
    }
    
    @Test  
    @Ignore
    public void listar(){
        CategoriaDAO dao = new CategoriaDAO();
        for(Categoria ca: dao.findAll()){
            System.out.println("Categoria: " + ca.getDescricao());
            System.out.println("ID Categoria: " + ca.getId());
        }
    }
    
    @Test
    @Ignore
    public void editar(){        
        Categoria cat = new Categoria("Roupas");
        cat.setId(4);
        CategoriaDAO dao = new CategoriaDAO();
        
        if(dao.update(cat)){
            System.out.println("Editado com sucesso!");
        }else{
            fail("Erro ao salvar");
        }
    }
    
    @Test
    public void excluir(){
        Categoria cat = new Categoria();
        cat.setId(5);
        CategoriaDAO dao = new CategoriaDAO();
        if (dao.delete(cat)) {
            System.out.println("Excluído com sucesso");
        } else {
            fail("Erro ao excluir");
        }
    }
}
