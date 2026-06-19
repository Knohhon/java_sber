package lab3;

import java.util.*;

public class CollectionBenchmark {

    static final int  N       = 16_000_000;
    static final long GET_OPS = 16_000_000_000L;
    static final int  M       = 1_000_000;   // повторов для усреднения «быстрых» операций

    public static void main(String[] args) {
        String kind = args.length > 0 ? args[0].toLowerCase() : "all";
        System.out.printf("=== N = %,d | GET_OPS = %,d ===%n", N, GET_OPS);
        switch (kind) {
            case "arraylist"     -> benchArrayList();
            case "linkedhashmap" -> benchLinkedHashMap();
            case "treeset"       -> benchTreeSet();
            default -> { benchArrayList(); benchLinkedHashMap(); benchTreeSet(); }
        }
    }

    // ==================================================================
    //  ArrayList
    // ==================================================================
    static void benchArrayList() {
        System.out.println("\n--- ArrayList ---");
        warmupList();
        print("1. Добавление в начало",   oNAddFront(true));
        print("2. Добавление в конец",    avgListAddEnd());
        print("3. Добавление в середину", oNAddMid(true));
        print("4. Удаление из начала",    oNRemoveFront(true));
        print("5. Удаление из конца",     avgListRemoveEnd());
        print("6. Удаление из середины",  oNRemoveMid(true));

        // 7. Получение по индексу — полные GET_OPS операций
        ArrayList<Integer> list = newList(N);
        long acc = 0;
        for (int w = 0; w < 3; w++) for (int i = 0; i < N; i++) acc += list.get(i); // прогрев JIT
        long t0 = System.nanoTime();
        int idx = 0;
        for (long i = 0; i < GET_OPS; i++) { acc += list.get(idx); if (++idx >= N) idx = 0; }
        long t1 = System.nanoTime();
        System.out.printf("%-28s : %,12.1f нс/оп   (всего %,d оп. за %.2f c)%n",
                "7. Получение по индексу", (t1 - t0) / (double) GET_OPS, GET_OPS, (t1 - t0) / 1e9);
        sink(acc);
    }

    static ArrayList<Integer> newList(int n) {
        ArrayList<Integer> l = new ArrayList<>(n);
        for (int i = 0; i < n; i++) l.add(i);
        return l;
    }
    // O(n): минимум из 3 прогонов, gc() перед каждым — чтобы пауза сборщика
    //       мусора не попадала в окно измерения единичной операции.
    static double oNAddFront(boolean x) {
        double best = Double.MAX_VALUE;
        for (int r = 0; r < 3; r++) { ArrayList<Integer> l = newList(N); System.gc();
            long t = System.nanoTime(); l.add(0, -1);      best = Math.min(best, System.nanoTime() - t); }
        return best;
    }
    static double oNAddMid(boolean x) {
        double best = Double.MAX_VALUE;
        for (int r = 0; r < 3; r++) { ArrayList<Integer> l = newList(N); System.gc();
            long t = System.nanoTime(); l.add(N / 2, -1);  best = Math.min(best, System.nanoTime() - t); }
        return best;
    }
    static double oNRemoveFront(boolean x) {
        double best = Double.MAX_VALUE;
        for (int r = 0; r < 3; r++) { ArrayList<Integer> l = newList(N); System.gc();
            long t = System.nanoTime(); l.remove(0);       best = Math.min(best, System.nanoTime() - t); }
        return best;
    }
    static double oNRemoveMid(boolean x) {
        double best = Double.MAX_VALUE;
        for (int r = 0; r < 3; r++) { ArrayList<Integer> l = newList(N); System.gc();
            long t = System.nanoTime(); l.remove(N / 2);   best = Math.min(best, System.nanoTime() - t); }
        return best;
    }
    static double avgListAddEnd() {     // O(1) — усредняем по M операций
        ArrayList<Integer> l = newList(N); System.gc();
        long t = System.nanoTime();
        for (int i = 0; i < M; i++) l.add(i);
        return (System.nanoTime() - t) / (double) M;
    }
    static double avgListRemoveEnd() {  // O(1)
        ArrayList<Integer> l = newList(N); System.gc();
        long t = System.nanoTime();
        for (int i = 0; i < M; i++) l.remove(l.size() - 1);
        return (System.nanoTime() - t) / (double) M;
    }
    static void warmupList() {
        ArrayList<Integer> l = newList(500_000);
        for (int i = 0; i < 50_000; i++) { l.add(0, i); l.remove(0); l.add(i); l.remove(l.size() - 1); }
    }

    // ==================================================================
    //  LinkedHashMap   (ключи 0..N-1, порядок = порядок вставки)
    // ==================================================================
    static void benchLinkedHashMap() {
        System.out.println("\n--- LinkedHashMap ---");
        warmupMap();
        print("1. Добавление в начало",   mapRebuildFront());  // O(n) — пересоздание
        print("2. Добавление в конец",    mapAddEnd());        // O(1)
        print("3. Добавление в середину", mapRebuildMid());    // O(n) — пересоздание
        print("4. Удаление из начала",    mapRemove(0));       // O(1) (по ключу)
        print("5. Удаление из конца",     mapRemove(2));       // O(1)
        print("6. Удаление из середины",  mapRemove(1));       // O(1)

        // 7. Доступа по индексу нет → меряем получение по ключу get(key), O(1)
        LinkedHashMap<Integer,Integer> map = newMap(N);
        long acc = 0;
        for (int w = 0; w < 2; w++) for (int i = 0; i < N; i++) acc += map.get(i); // прогрев
        long t0 = System.nanoTime();
        int key = 0;
        for (long i = 0; i < GET_OPS; i++) { acc += map.get(key); if (++key >= N) key = 0; }
        long t1 = System.nanoTime();
        System.out.printf("%-28s : %,12.1f нс/оп   (всего %,d оп. за %.2f c)%n",
                "7. Получение по ключу", (t1 - t0) / (double) GET_OPS, GET_OPS, (t1 - t0) / 1e9);
        sink(acc);
    }

    static LinkedHashMap<Integer,Integer> newMap(int n) {
        LinkedHashMap<Integer,Integer> m = new LinkedHashMap<>(n * 4 / 3 + 1);
        for (int i = 0; i < n; i++) m.put(i, i);
        return m;
    }
    // Вставка в начало порядка обхода = пересоздание карты, O(n).
    static double mapRebuildFront() {
        int[] keys = keysOf(newMap(N));         // снимок ключей в порядке вставки
        System.gc();
        long t = System.nanoTime();
        LinkedHashMap<Integer,Integer> nm = new LinkedHashMap<>(N * 4 / 3 + 1);
        nm.put(-1, -1);
        for (int k : keys) nm.put(k, k);
        long dt = System.nanoTime() - t;
        sink(nm.size());
        return dt;
    }
    static double mapRebuildMid() {
        int[] keys = keysOf(newMap(N));
        System.gc();
        long t = System.nanoTime();
        LinkedHashMap<Integer,Integer> nm = new LinkedHashMap<>(N * 4 / 3 + 1);
        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length / 2) nm.put(-1, -1);
            nm.put(keys[i], keys[i]);
        }
        long dt = System.nanoTime() - t;
        sink(nm.size());
        return dt;
    }
    static int[] keysOf(LinkedHashMap<Integer,Integer> m) {
        int[] keys = new int[m.size()]; int p = 0;
        for (int k : m.keySet()) keys[p++] = k;
        return keys;
    }
    static double mapAddEnd() {              // O(1)
        LinkedHashMap<Integer,Integer> m = newMap(N); System.gc();
        long t = System.nanoTime();
        for (int i = 0; i < M; i++) m.put(N + i, i);
        return (System.nanoTime() - t) / (double) M;
    }
    // where: 0 — начало порядка, 1 — середина, 2 — конец. Удаление по ключу — O(1).
    static double mapRemove(int where) {
        LinkedHashMap<Integer,Integer> m = newMap(N);
        int base = switch (where) { case 0 -> 0; case 1 -> N / 2; default -> N - M; };
        System.gc();
        long t = System.nanoTime();
        for (int i = 0; i < M; i++) m.remove(base + i);
        return (System.nanoTime() - t) / (double) M;
    }
    static void warmupMap() {
        LinkedHashMap<Integer,Integer> m = newMap(500_000);
        long s = 0;
        for (int i = 0; i < 100_000; i++) { m.put(600_000 + i, i); s += m.get(i); m.remove(i); }
        int[] keys = new int[1_000_000];
        for (int i = 0; i < keys.length; i++) keys[i] = i;
        for (int r = 0; r < 2; r++) {            // прогрев пути пересоздания карты
            LinkedHashMap<Integer,Integer> nm = new LinkedHashMap<>(keys.length * 4 / 3 + 1);
            nm.put(-1, -1);
            for (int k : keys) nm.put(k, k);
            s += nm.size();
        }
        sink(s);
    }

    // ==================================================================
    //  TreeSet (SortedSet)   значения 0,2,4,...,2(N-1) — с «дырами» под вставку
    // ==================================================================
    static void benchTreeSet() {
        System.out.println("\n--- TreeSet (SortedSet) ---");
        warmupSet();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < N; i++) set.add(2 * i);

        print("1. Добавление в начало",   setAdd(set, -1, -2));             // наименьшие → начало
        print("2. Добавление в конец",    setAdd(set, 2 * N + 1, 2));       // наибольшие → конец
        print("3. Добавление в середину", setAdd(set, 2 * (N / 2) + 1, 2)); // средние (нечётные)
        print("4. Удаление из начала",    setPollFirst(set));               // O(log n)
        print("5. Удаление из конца",     setPollLast(set));                // O(log n)
        print("6. Удаление из середины",  setRemoveMid(set));               // O(log n)

        // 7. Доступа по индексу у TreeSet НЕТ. Ближайший аналог — contains(), O(log n).
        long t0 = System.nanoTime(); long hit = 0;
        for (int i = 0; i < M; i++) if (set.contains(2 * ((i * 31) % N))) hit++;
        long t1 = System.nanoTime();
        System.out.printf("%-28s : %s%n",
                "7. Получение по индексу", "— не поддерживается (нет индексного доступа)");
        System.out.printf("%-28s : %,12.1f нс/оп   (аналог поиска по значению)%n",
                "   contains()", (t1 - t0) / (double) M);
        sink(hit);
    }

    static double setAdd(TreeSet<Integer> set, int start, int step) {   // O(log n)
        long t = System.nanoTime(); int v = start;
        for (int i = 0; i < M; i++) { set.add(v); v += step; }
        return (System.nanoTime() - t) / (double) M;
    }
    static double setPollFirst(TreeSet<Integer> set) {
        long t = System.nanoTime();
        for (int i = 0; i < M; i++) set.pollFirst();
        return (System.nanoTime() - t) / (double) M;
    }
    static double setPollLast(TreeSet<Integer> set) {
        long t = System.nanoTime();
        for (int i = 0; i < M; i++) set.pollLast();
        return (System.nanoTime() - t) / (double) M;
    }
    static double setRemoveMid(TreeSet<Integer> set) {
        long t = System.nanoTime(); int v = 2 * (set.size() / 2);
        for (int i = 0; i < M; i++) { set.remove(v); v += 2; }
        return (System.nanoTime() - t) / (double) M;
    }
    static void warmupSet() {
        TreeSet<Integer> s = new TreeSet<>();
        for (int i = 0; i < 200_000; i++) s.add(2 * i);
        for (int i = 0; i < 100_000; i++) { s.add(-i); s.pollFirst(); s.contains(2 * i); }
    }

    // ==================================================================
    static void print(String name, double ns) {
        System.out.printf("%-28s : %,12.1f нс/оп%n", name, ns);
    }
    static long BLACKHOLE;                 // чтобы JIT не выкинул «мёртвый» код
    static void sink(long v) { BLACKHOLE += v; }
}