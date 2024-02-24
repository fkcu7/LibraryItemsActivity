/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itcc11.libraryitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Student
 */
interface LibraryItems
{
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}
abstract class LibraryUser 
{
    LibraryItems[] borrowedItems;
    LibraryUser()
    {
        this.borrowedItems = new LibraryItems[5];
    }
    LibraryItems[] getBorrowedItems()
    {
        return borrowedItems;
    }
    void borrowItem(LibraryItems item)
    {
        for(int x = 0; x < borrowedItems.length; x++)
        {
            if (borrowedItems[x] == null)
            {
                borrowedItems[x] = item;
                item.borrowItem();
                return;
            }
        }
        System.out.println("User cannot borrow more items.");
    }
    void returnItem(LibraryItems item)
    {
        for(int x = 0; x < borrowedItems.length; x++)
        {
            if (borrowedItems[x] == null)
            {
                borrowedItems[x] = item;
                item.returnItem();
                return;
            }
        }
        System.out.println("User did not borrow this item.");
    }
    abstract void printItemsBorrowed();
}
class Student extends LibraryUser
{ 
    @Override
    void printItemsBorrowed() 
    {
        System.out.println("Student borrowed items:");
        for(LibraryItems item : getBorrowedItems())
        {
            if (item != null)
            {
                System.out.println(" - " + item);
            }
        }
        System.out.println();
    }   
}
class Teacher extends LibraryUser
{
    @Override
    void printItemsBorrowed() 
    {
         System.out.println("Teacher borrowed items:");
        for(LibraryItems item : getBorrowedItems())
        {
            if (item != null)
            {
                System.out.println(" - " + item);
            }
        }
        System.out.println();
    }
}
class Books implements LibraryItems
{
    private String Title, Author;
    private Boolean borrowed;
    Books(String Title, String Author)
    {
        this.Title = Title;
        this.Author = Author;
    }
    String getTitle()
    {
        return Title;
    }
    String getAuthor()
    {
        return Author;
    }
    void setTitle(String title)
    {
        Title = title;
    }
    void setAuthor(String author)
    {
        Author = author;
    }
    public String toString()
    {
        return "Title: " + Title + " by " + Author;
    }

    @Override
    public void borrowItem() 
    {
        borrowed = true;
    }

    @Override
    public void returnItem() 
    {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() 
    {
        return borrowed;
    }   
}
class DVD implements LibraryItems
{
    private String Title, Author;
    private Boolean borrowed;

    DVD(String Title, String Author)
    {
        this.Title = Title;
        this.Author = Author;
    }
    String getTitle()
    {
        return Title;
    }
    String getAuthor()
    {
        return Author;
    }
    void setTitle(String title)
    {
        Title = title;
    }
    void setAuthor(String author)
    {
        Author = author;
    }
    public String toString()
    {
        return "Title: " + Title + " by " + Author;
    }
    @Override
    public void borrowItem() 
    {
        borrowed = true;
    }

    @Override
    public void returnItem()
    {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() 
    {
        return borrowed;
    }  
}
public class Libraryitem 
{
    public static void main(String[] args) 
    {
        Books book = new Books("Harry Potter","J.K. Rowling");
        DVD dvd = new DVD("Fifty Shades of Grey","Sam Taylor-Johnson");
        
        Student s = new Student();
        Teacher t = new Teacher();
        
        s.borrowItem(book);
        t.borrowItem(dvd);
        
        s.printItemsBorrowed();
        t.printItemsBorrowed();
        
        s.returnItem(dvd);
        t.returnItem(dvd);
    }
}
/*
class ItemList
{
    private String StudentName, Title, Author;
    ItemList(String StudentName, String Title, String Author)
    {
        this.StudentName = StudentName;
        this.Title = Title;
        this.Author = Author;
    }
    void setStudentName(String Name)
    {
        StudentName = Name;
    }
    void setTitle(String title)
    {
        Title = title;
    }
    void setAuthor(String author)
    {
        Author = author;
    }
    public String getStudentName()
    {
        return StudentName;
    }
    public String getTitle()
    {
        return Title;
    }
    public String getAuthor()
    {
        return Author;
    }
    public String toString()
    {
        return "Student Name: " + StudentName + ", Title: " + Title + ", Author: " + Author;
    }
}
class Books implements LibraryItems
{
    Scanner bs = new Scanner(System.in);
    List<ItemList> books = new ArrayList<>();
    Books()
    {
        System.out.println("=====================\nWhat would you like to do?\n1.Borrow\n2.Return\n3.Check\n4.Back\n5.exit\n=====================");
        int choice = bs.nextInt();
        switch(choice)
        {
            case 1:
            {
                borrowItem();
                break;
            }
            case 2:
            {
                returnItem();
                break;
            }
            case 3:
            {
                Boolean status = isBorrowed();
                if (status == false)
                {
                    System.out.println("The book is available for borrowing.");
                }
                else 
                {
                    System.out.println("The book is currently not available.");
                }
                break;
            }
            case 4:
            {
                new Libraryitem();
                break;
            }
            case 5:
            {
                System.exit(0);
                break;
            }
            default:
            {
                System.out.println("Choose among the choices given.");
                new Books();
            }
        }
    }

    @Override
    public void borrowItem() 
    {
        System.out.print("Student Name: ");
        String name = bs.nextLine();
        System.out.print("Title: ");
        String title = bs.nextLine();
        System.out.print("Author: ");
        String author = bs.nextLine();
        books.add(new ItemList(name,title,author));
    }

    @Override
    public void returnItem() 
    {
        System.out.print("Student Name: ");
        String name = bs.nextLine();
        String line = "";
        Iterator i = books.iterator();
        while (i.hasNext())
        {
            ItemList g = (ItemList) i.next();
            if (g.getStudentName().equals(name))
            {
                line += g.getTitle() + " by " + g.getAuthor() + "\n";
            }
        }
        if(line.equals(""))
        {
            System.out.println("Student " + name + " does not have any borrowed item from the library.");
            new Libraryitem();
        }
        else
        {
            System.out.println(line);
            System.out.println("What title of the book would you like to return?");
            String title = bs.nextLine();
            while (i.hasNext())
            {
                ItemList g = (ItemList) i.next();
                if (g.getTitle().equals(title))
                {
                    i.remove();
                    System.out.println("Return Successful\n");
                    new Libraryitem();
                }
                else
                {
                    System.out.println("Book Title not Found");
                    new Books();
                }
            }
        }
    }

    @Override
    public boolean isBorrowed() 
    {
        System.out.println("What title of the book would you like to status check");
        String title = bs.nextLine();
        Iterator i = books.iterator();
        while (i.hasNext())
        {
            ItemList g = (ItemList) i.next();
            
            if (g.getTitle().equals(title))
            {
                return true;
            }
        }
        return false;
    }
}
class DVD implements LibraryItems
{
    DVD()
    {
        
    }

    @Override
    public void borrowItem() 
    {
        
    }

    @Override
    public void returnItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isBorrowed() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
public class Libraryitem 
{
    public static void main(String[] args) 
    {
        Scanner cl = new Scanner(System.in);
        boolean loop;
        do
        {
            System.out.println("=====================\nOptions:\n1.Books\n2.DVDs\n3.Exit\n=====================");
            int option = cl.nextInt();
            switch (option)
            {
                case 1:
                {
                    new Books();
                    break;
                }
                case 2:
                {
                    new DVD();
                    break;
                }
                case 3:
                {
                    System.exit(0);
                    break;
                }
                default:
                {
                    System.out.println("Choose between the options given.");
                }
            }
        }
        while (loop = true);
    }
}

*/