package ch08_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p10026 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.valueOf(br.readLine());
		
		int[] dx = {1,0,-1,0};
		int[] dy = {0,1,0,-1};
		
		char[][] picture = new char[N][N];  // 그림 배열
		boolean[][] visited = new boolean[N][N];  // 적록색약 X 방문 여부 표시 배열
		boolean[][] visited_RG = new boolean[N][N];  // 적록색약 O 방문 여부 표시 배열
		
		Queue<Point> queue = new LinkedList<>();  // 큐 생성
		
		int count = 0;  // 적록색약 X 구역의 개수
		int RG = 0;  // 적록색약 O 구역의 개수 
		int B = 0;  // B의 개수 
		
		
		/*
		 * 그림 배열 값 세팅
		 */
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				picture[i][j] = input.charAt(j);
			}
		}
		
		/*
		 * 그림 배열 전체 순회하면서, 방문하지 않은 구역을 시작점으로 BFS 수행
		 */
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				/*
				 * 적록색약 X에 대한 BFS
				 */
				if (visited[i][j] == true) continue;  // 방문 여부 검증 
				queue.add(new Point(i, j));  // enqueue
				visited[i][j] = true;  // 방문 표시
				count++;
				
				char color = picture[i][j];  // 시작점의 색깔 color 
				if (color == 'B') B++;
				
				while (!queue.isEmpty()) {
					Point dequeue = queue.remove();  // dequeue
					
					for (int d = 0; d < 4; d++) {
						int dX = dequeue.X + dx[d];
						int dY = dequeue.Y + dy[d];
						
						if (dX < 0 || dX >= N || dY < 0 || dY >= N || visited[dX][dY] == true) continue;  // 좌표 유효성 검증, 방문 여부 검증 
					    if (picture[dX][dY] != color) continue;  // 시작점의 색깔과 비교
					    queue.add(new Point(dX, dY));  // enqueue
					    visited[dX][dY] = true;  // 방문 표시
					}
				}
				
				/*
				 * 적록색약 O에 대한 BFS
				 */
				if (visited_RG[i][j] == true || picture[i][j] == 'B') continue;  // 방문 여부, 색깔 검증 
				queue.add(new Point(i, j));  // enqueue
				visited_RG[i][j] = true;  // 방문 표시
				RG++;
				
				while (!queue.isEmpty()) {
					Point dequeue = queue.remove();  // dequeue
					
					for (int d = 0; d < 4; d++) {
						int dX = dequeue.X + dx[d];
						int dY = dequeue.Y + dy[d];
						
						if (dX < 0 || dX >= N || dY < 0 || dY >= N || visited_RG[dX][dY] == true) continue;  // 좌표 유효성 검증, 방문 여부 검증 
					    if (picture[dX][dY] == 'B') continue;  // 시작점의 색깔과 비교
					    queue.add(new Point(dX, dY));  // enqueue
					    visited_RG[dX][dY] = true;  // 방문 표시
					}
				}
			}
		}
		System.out.println(count + " " + (RG + B));
	}
}

class Point {
	int X;
	int Y;
	
	Point(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}
}

