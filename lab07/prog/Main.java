package lab07.prog;

import java.io.*;
import java.util.*;

import lab07.classes.*;




public class Main
{


    private static ArrayList<Beer> beers = new ArrayList<Beer>();
    private static HashMap<String, Command> commands = new HashMap<>();
    private static Map<String, Comparator<Beer>> comparators = new HashMap<>();
    private static ArrayList<String> params = new ArrayList<String>();










    public static void add(String[] cmd)
    {
        beers.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
    }




    public static void list(String[] cmd)
    {
        params.remove(cmd[1]);
        params.add(0, cmd[1]);
        Collections.sort(beers, comparators.get(params.get(0)).thenComparing(comparators.get(params.get(1)).thenComparing(comparators.get(params.get(2)))));
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }




    public static void search(String[] cmd)
    {
        for (Beer beer : beers)
        {
            if (beer.getName().toLowerCase().equals(cmd[1].toLowerCase()))
            {
                System.out.println(beer.getName() + " " + beer.getStyle() + " " + beer.getStrength());
            }
        }
    }




    public static void find(String[] cmd)
    {
        for (Beer beer : beers)
        {
            if (beer.getName().toLowerCase().contains(cmd[1].toLowerCase()))
            {
                System.out.println(beer.getName() + " " + beer.getStyle() + " " + beer.getStrength());
            }
        }
    }




    public static void delete(String[] cmd)
    {
        Iterator<Beer> it = beers.iterator();
        while(it.hasNext()){

            Beer beer = it.next();
            if (cmd.length == 2)
            {
                if (beer.getName().toLowerCase().equals(cmd[1].toLowerCase()))
                {
                    it.remove();
                }
            }
            else if (cmd.length == 3)
            {
                if (cmd[1].equals("style"))
                {
                    if (beer.getStyle().toLowerCase().equals(cmd[2].toLowerCase()))
                    {
                        it.remove();
                    }
                }
                else if (cmd[1].equals("strength"))
                {
                    if (beer.getStrength() == Double.parseDouble(cmd[2]))
                    {
                        it.remove();
                    }
                }
            }


        }
    }




    public static void save(String[] cmd) throws IOException
    {
        FileOutputStream fo = new FileOutputStream("./files/" + cmd[1] + ".ser");
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(beers);
        so.close();

        System.out.println("Beers have been saved!");
    }



    @SuppressWarnings (value="unchecked")
    public static void load(String[] cmd) throws IOException, ClassNotFoundException
    {
        FileInputStream fi = new FileInputStream("./files/" + cmd[1] + ".ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
        beers = (ArrayList<Beer>) oi.readObject();
        oi.close();
    }


    public static void exit(String[] cmd)
    {
        System.exit(0);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Beer beer1 = new Beer("name1", "style1", 5.5);
        Beer beer2 = new Beer("name2", "style2", 7.2);

        System.out.println(beer1);
        System.out.println(beer2);

        commands.put("add", Main::add);
        commands.put("exit", Main::exit);
        commands.put("list", Main::list);
        commands.put("save", Main::save);
        commands.put("load", Main::load);
        commands.put("search", Main::search);
        commands.put("find", Main::find);
        commands.put("delete", Main::delete);

        comparators.put("name", new NameComparator());
        comparators.put("strength", new StrengthComparator());
        comparators.put("style", new StyleComparator());

        params.add("name");
        params.add("strength");
        params.add("style");


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            System.out.print(">>> ");
            String[] line = br.readLine().split(" ");


            if (commands.containsKey(line[0])) {
                commands.get(line[0]).execute(line);
            } else {
                System.out.println("err");
            }

        }

        // sc.close();

    }



}
