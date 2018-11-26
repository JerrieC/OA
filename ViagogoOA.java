import java.util.*;

public class ViagogoOA {
  public static class Event {
    int x, y, id, distance;
    List<Integer> price;
  }
  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(System.in);
    int sizeOfWord = Integer.parseInt(scan.nextLine());
    int numberOfEvents = Integer.parseInt(scan.nextLine());

    Comparator<Event> comparator = new Comparator<Event>() {
      @Override
      public int compare(Event e1, Event e2) {
        if (e1.distance > e2.distance) return 1;
        else if (e1.distance < e2.distance) return -1;
        else if (e1.price.get(0) > e2.price.get(0)) return 1;
        else if (e1.price.get(0) < e2.price.get(0)) return -1;
        else if (e1.id < e2.id) return 1;
        else if (e1.id > e2.id) return -1;
        else return 0;
      }
    };

    Queue<Event> queue = new PriorityQueue<>(numberOfEvents, comparator);

    List<Event> events = new ArrayList<>();
    while (numberOfEvents > 0) {
      String eventLine = scan.nextLine();
      String[] event = eventLine.split(" ");
      Event e = new Event();
      e.price = new ArrayList<>();
      for (int i=0; i<event.length; i++) {
        if (i==0) e.id = Integer.parseInt(event[i]);
        else if (i==1) e.x = Integer.parseInt(event[i]);
        else if (i==2) e.y = Integer.parseInt(event[i]);
        else e.price.add(Integer.parseInt(event[i]));
      }
      Collections.sort(e.price);
      events.add(e);
      numberOfEvents--;
    }

    int numberOfBuyers = Integer.parseInt(scan.nextLine());
    int count = 1;
    while (numberOfBuyers > 0) {
      queue.clear();
      String buyerLine = scan.nextLine();
      int x1 = Integer.parseInt(buyerLine.split(" ")[0]);
      int y1 = Integer.parseInt(buyerLine.split(" ")[1]);
      for (Event event : events) {
        event.distance = calculateManhattanDistance(x1, y1, event.x, event.y);
        queue.add(event);
      }
      // result
      Event result = queue.poll();
      System.out.println(result.id + " " + result.price.get(0));
      if (result.price.size()>1) result.price.remove(0);
      else events.remove(result);

      count++;
      numberOfBuyers--;
    }
  }

  public static int calculateManhattanDistance(int x1, int y1, int x2, int y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }
}
