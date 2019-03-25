/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

/**
 *
 * @author cdiaz
 */
public class ListaCurso {
    private String id;
    private String anno;
    private String carreraId;
    private String cursoId;
    private String cicloId;

    public ListaCurso() {
    }

    public ListaCurso(String id, String anno, String carreraId, String cursoId, String cicloId) {
        this.id = id;
        this.anno = anno;
        this.carreraId = carreraId;
        this.cursoId = cursoId;
        this.cicloId = cicloId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(String carreraId) {
        this.carreraId = carreraId;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getCicloId() {
        return cicloId;
    }

    public void setCicloId(String cicloId) {
        this.cicloId = cicloId;
    }

   
    @Override
    public String toString() {
        return "ListaCurso{" + "id=" + id + 
                ", anno=" + anno + 
                ", carreraId=" + carreraId + 
                ", cursoId=" + cursoId + 
                ", cicloId=" + cicloId + 
                '}';
    }

    
}
