import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class timus_1821 {

    public static class BiathlonLeaderboard {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine().trim());

            String[] names = new String[n];
            long[] raceTime = new long[n];   // время прохождения дистанции (в десятых долях секунды)
            long[] finishTime = new long[n]; // реальное время финиша (старт + дистанция)

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                names[i] = st.nextToken();
                String timeStr = st.nextToken();

                raceTime[i] = parseTimeToTenths(timeStr);
                long startOffset = (long) i * 30 * 10; // 30 секунд интервала, в десятых долях
                finishTime[i] = startOffset + raceTime[i];
            }

            // Сортируем индексы участников по моменту реального финиша
            Integer[] order = new Integer[n];
            for (int i = 0; i < n; i++) {
                order[i] = i;
            }
            Arrays.sort(order, (a, b) -> Long.compare(finishTime[a], finishTime[b]));

            List<String> leaders = new ArrayList<>();
            long minRaceTime = Long.MAX_VALUE;

            for (int idx : order) {
                if (raceTime[idx] < minRaceTime) {
                    minRaceTime = raceTime[idx];
                    leaders.add(names[idx]);
                }
            }

            Collections.sort(leaders);

            StringBuilder sb = new StringBuilder();
            sb.append(leaders.size()).append('\n');
            for (String name : leaders) {
                sb.append(name).append('\n');
            }

            System.out.print(sb);
        }

        // Парсинг строки "mm:ss.d" в десятые доли секунды
        private static long parseTimeToTenths(String timeStr) {
            int colonIdx = timeStr.indexOf(':');
            int dotIdx = timeStr.indexOf('.');

            int minutes = Integer.parseInt(timeStr.substring(0, colonIdx));
            int seconds = Integer.parseInt(timeStr.substring(colonIdx + 1, dotIdx));
            int tenths = Integer.parseInt(timeStr.substring(dotIdx + 1));

            return ((long) minutes * 60 + seconds) * 10 + tenths;
        }
    }
}
