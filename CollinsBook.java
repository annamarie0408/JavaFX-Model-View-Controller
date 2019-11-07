//Anna Collins
//8/22/19
//Java Advanced Assignment 1
public class CollinsBook {

    private String publisher;
    private String title;
    private String ISBN;
    private String imageName;
    private Double price;


    public CollinsBook(String publisher, String title, String ISBN, String imageName, Double price) throws CollinsInvalidISBN {
        this.publisher = publisher;
        this.title = title;
        try {
            setISBN(ISBN);
        }
        catch (CollinsInvalidISBN ex){
            throw new CollinsInvalidISBN("");
        }

        this.imageName = imageName;
        this.price = price;
    }

    public String getPublisher() {return publisher;}

    public void setPublisher(String publisher) {this.publisher = publisher; }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getISBN() {return ISBN;}

    public void setISBN(String ISBN) throws CollinsInvalidISBN
    {
       this.ISBN = ISBN;
        ISBN = ISBN.replaceAll("-","");
        int total = 0;
            for (int i = 0; i < 12; i++){
                int digit = Integer.parseInt(ISBN.substring(i, i+1));
                total += (i % 2 == 0) ? digit * 1 : digit * 3;
            }
        int checksum = 10 - (total % 10);

        if (checksum != Integer.parseInt(ISBN.substring( 12 ) ))
        {
            throw new CollinsInvalidISBN("");
        }
    }



    public String getImageName() {return imageName;}

    public void setImageName(String imageName) {this.imageName = imageName;}

    public Double getPrice() {return price;}

    public void setPrice(String Double) { this.price = price;}



    @Override
    public String toString() {
        return "'CollinsBooks' " +
                "publisher = " + publisher +
                ", title = " + title +
                ", ISBN = " + ISBN +
                ", imageName = " + imageName +
                ", price = $" + price;

    }
}
