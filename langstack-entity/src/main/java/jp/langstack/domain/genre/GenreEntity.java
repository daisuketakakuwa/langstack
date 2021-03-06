package jp.langstack.domain.genre;
// Generated 2021/11/11 21:26:33 by Hibernate Tools 5.3.20.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jp.langstack.domain.card.CardEntity;

/**
 * GenreEntity generated by hbm2java
 */
@Entity
@Table(name="genre"
)
public class GenreEntity  implements java.io.Serializable {


     private String id;
     private String name;
     private Set<CardEntity> cards = new HashSet<CardEntity>(0);

    public GenreEntity() {
    }

	
    public GenreEntity(String id) {
        this.id = id;
    }
    public GenreEntity(String id, String name, Set<CardEntity> cards) {
       this.id = id;
       this.name = name;
       this.cards = cards;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=22)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="genre")
    public Set<CardEntity> getCards() {
        return this.cards;
    }
    
    public void setCards(Set<CardEntity> cards) {
        this.cards = cards;
    }




}


