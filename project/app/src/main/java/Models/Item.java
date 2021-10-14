package Models;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Item {
    private final int id;
    private final String name;
    private final String description;
    private final String quantity;
    private final double price;
    private final String img;
    Item(int id,String name,String description,String quantity,double price,String img){
        this.id=id;
        this.name=name;
        this.description=description;
        this.quantity=quantity;
        this.price=price;
        this.img=img;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }
}
