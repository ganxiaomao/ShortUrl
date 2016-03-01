import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;


public class Test {
	public static void main(String[] args) {
		List<Thread> list = new ArrayList<Thread>();
		final String[]  domain = {"localhost","127.0.0.1"};
		for (int i = 0; i < 100; i++) {
			list.add(new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
//						HttpRequest.sendGet("http://localhost:8000/ShortUrlWeb/api/create.json", "serviceId="+ RandomUtils.nextInt(1, 3) +"&longUrl=http%3A%2F%2Fwww.doyo"+ RandomUtils.nextInt(1, 1000000) +"uhike.net%2F&alias=");
						HttpRequest.sendGet("http://"+ domain[RandomUtils.nextInt(0, 2)] +"/VrmU3i", "");
					}
				}
			}));
		}
		long sTime = System.currentTimeMillis();
		for (Thread thread : list) {
			thread.start();
		}
		for (Thread thread : list) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println((System.currentTimeMillis() - sTime) / 1000);
	}
}
