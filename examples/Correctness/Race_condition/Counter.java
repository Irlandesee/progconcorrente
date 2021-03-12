public class Counter {
   long count = 0;
   public void add (long value) {
      long tmp = this.count;
      tmp = tmp + value;
      this.count = tmp ;
   }
}
