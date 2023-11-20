import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simple_executor_test {

    private static final int NTHREADS = 8;
        
    public static void main(String[] args)  {
		double dx = 0.001;
		double sum = 0.0, sum_seq = 0.0;

		try {

//		Counter counter = new Counter();
			ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

			double my_stride = (Math.PI - 0) / 50;
			double my_start = 0;
			double my_end = my_stride;

			List<Future<Double>> future_list = new ArrayList<>();

			for (int i = 0; i < 50; i++) {
//			Runnable worker = new CounterPlus(counter);
//			executor.execute(worker);

				Calka_callable calka1 = new Calka_callable(my_start, my_end, dx);
				sum_seq += calka1.compute_integral();

				my_start = my_end;
				my_end += my_stride;

				Future<Double> f = executor.submit(calka1);
				future_list.add(f);
			}

			for (Future<Double> x : future_list) {
				double res = x.get();
				sum += res;
			}

			// This will make the executor accept no new threads
			// and finish all existing threads in the queue
			executor.shutdown();

			// Wait until all threads finish
			while (!executor.isTerminated()) {
			}

			System.out.println("Calka seq: " + sum_seq);
			System.out.println("Calka watki: " + sum);
			System.out.println("Finished all threads");
//		System.out.format("\nCounter_1: %d, Counter_2 %d\n\n",
//				  counter.get_c1(), counter.get_c2());
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
} 
