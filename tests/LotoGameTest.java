package com.madamgames.lotomada;
public final class LotoGameTest {
  static void ok(boolean x,String m){if(!x)throw new AssertionError(m);}
  public static void main(String[] a){
    LotoGame g=new LotoGame(new java.util.Random(7)); g.newTicket(5); g.draw(5);
    ok(g.ticket().size()==5,"ticket size"); ok(g.draw().size()==5,"draw size");
    ok(new java.util.HashSet<>(g.ticket()).size()==5,"unique ticket");
    ok(new java.util.HashSet<>(g.draw()).size()==5,"unique draw"); ok(g.matches()>=0&&g.matches()<=5,"match range");
    System.out.println("LOTO_TEST_PASS");
  }
}
