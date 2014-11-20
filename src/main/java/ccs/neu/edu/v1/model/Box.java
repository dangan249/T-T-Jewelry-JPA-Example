package ccs.neu.edu.v1.model;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Double;
import java.lang.String;
import java.util.Collection;


@Table(name="box")
@Entity(name = "BOX") //Name of the entity
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name="id",referencedColumnName="id")
public class Box extends Grouping {

  private Double maximumWeight;
  private Double volume;
  private int fulfills;

  @ManyToOne(optional=false)
  @JoinColumn(name="fulfills",referencedColumnName="id")
  private Invoice invoice;

  public Double getMaximumWeight() {
    return maximumWeight;
  }

  public void setMaximumWeight(Double maximumWeight) {
    this.maximumWeight = maximumWeight;
  }

  public Double getVolume() {
    return volume;
  }

  public void setVolume(Double volume) {
    this.volume = volume;
  }

  public int getFulfills() {
    return fulfills;
  }

  public void setFulfills(int fulfills) {
    this.fulfills = fulfills;
  }


  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  @Override
  public String toString() {
    return "Box{" +
        "maximumWeight=" + maximumWeight +
        ", volume=" + volume +
        ", fulfills=" + fulfills +
        '}';
  }

  @Entity(name = "PRODUCT")
  public static class Product implements Serializable {

    @Id //signifies the primary key
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "volume")
    private Double volume;

    @OneToMany(mappedBy="product",targetEntity=Containment.class,fetch=FetchType.EAGER)
    private Collection<Containment> containments;


    public Double getWeight() {
      return weight;
    }

    public void setWeight(Double weight) {
      this.weight = weight;
    }

    public Double getVolume() {
      return volume;
    }

    public void setVolume(Double volume) {
      this.volume = volume;
    }

    public Collection<Containment> getContainments() {
      return containments;
    }

    public void setContainments(Collection<Containment> containments) {
      this.containments = containments;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return "Product{" +
          "id=" + id +
          ", weight=" + weight +
          ", volume=" + volume +
          ", containments=" + containments +
          '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Product product = (Product) o;

      if (id != product.id) return false;
      if (containments != null ? !containments.equals(product.containments) : product.containments != null) return false;
      if (volume != null ? !volume.equals(product.volume) : product.volume != null) return false;
      if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;

      return true;
    }

    @Override
    public int hashCode() {
      int result = id;
      result = 31 * result + (weight != null ? weight.hashCode() : 0);
      result = 31 * result + (volume != null ? volume.hashCode() : 0);
      result = 31 * result + (containments != null ? containments.hashCode() : 0);
      return result;
    }
  }
}
