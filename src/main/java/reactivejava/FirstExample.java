package reactivejava;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;

public class FirstExample {

	public void emit() {
		Observable.just(1,2,3,4,5,6)
		.subscribe(data->System.out.println(data));
//		.subscribe(System.out::println);
	}
	
	private void code_2_3() {
		Observable<Integer> source = Observable.create(
				(ObservableEmitter<Integer> emitter) -> {
					emitter.onNext(100);
					emitter.onNext(200);
					emitter.onNext(300);
					emitter.onComplete();
				});
		
		source.subscribe(System.out::println);
	}
	
	private void code_2_4() {
		Observable<Integer> source = Observable.create(
				(ObservableEmitter<Integer> emitter) -> {
					emitter.onNext(100);
					emitter.onNext(200);
					emitter.onNext(300);
					emitter.onComplete();
				});
	}
	
	private void code_2_5() {
		Observable<Integer> source = Observable.create(
				(ObservableEmitter<Integer> emitter) -> {
					emitter.onNext(100);
					emitter.onNext(200);
					emitter.onNext(300);
					emitter.onComplete();
				});
		source.subscribe(System.out::println);
	}
	
	private void code_2_6() {
		Observable<Integer> source = Observable.create(
				(ObservableEmitter<Integer> emitter) -> {
					emitter.onNext(100);
					emitter.onNext(200);
					emitter.onNext(300);
					emitter.onComplete();
				});
		
		source.subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) throws Exception {
				System.out.println("Result :: " + t);				
			}
		});
	}
	
	private void code_2_7() {
		Integer[] arr = {100, 200, 300};
		Observable<Integer> source = Observable.fromArray(arr);
		source.subscribe(System.out::println);
	}
	
	private void code_2_8() {
		int[] arr = {100, 200, 300};
		Observable source = Observable.fromArray(arr);
		source.subscribe(System.out::println);
	}
	
	private void code_2_9() {
		int[] arr = {100, 200, 300};
		Observable<Integer> source = Observable.fromArray(toIntegerArray(arr));
		source.subscribe(System.out::println);
	}
	
	private void code_2_10() {
		ArrayList<String> names = new ArrayList<>();
		names.add("Jerry");
		names.add("William");
		names.add("Bob");
		
		Observable<String> source = Observable.fromIterable(names);
		source.subscribe(System.out::println);
	}
	
	private void code_2_11() {
		HashSet<String> cities = new HashSet<>();
		cities.add("Seoul");
		cities.add("London");
		cities.add("Paris");
		
		Observable<String> source = Observable.fromIterable(cities);
		source.subscribe(System.out::println);
	}
	
	private void code_2_12() {
		ArrayBlockingQueue<Order> orderQueue = new ArrayBlockingQueue<>(100);
		orderQueue.add(new Order("ORD-1"));
		orderQueue.add(new Order("ORD-2"));
		orderQueue.add(new Order("ORD-3"));
		
		Observable<Order> source = Observable.fromIterable(orderQueue);
		source.subscribe(order -> System.out.println(order.getId()));
	}
	
	private void code_2_14() {
		Callable<String> callable = () -> {
			System.out.println("Ready...");
			Thread.sleep(1000);
			return "Hello Callable!";
		};
		
		Observable<String> source = Observable.fromCallable(callable);
		source.subscribe(System.out::println);
	}
	
	private void code_2_16() {
		Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
			System.out.println("Ready...");
			Thread.sleep(1000);
			return "Hello Future!!!";
		});
		
		Observable<String> source = Observable.fromFuture(future);
		source.subscribe(System.out::println);
	}
	
	private void code_2_17() {
		Publisher<String> publisher = (Subscriber<? super String> s) -> {
			s.onNext("Hello, Obserable.fromPublisher()");
			s.onComplete();
		};
		
		Observable<String> source = Observable.fromPublisher(publisher);
		source.subscribe(System.out::println);
	}
	
	private void code_2_19() {
		Single<String> source = Single.just("Hello Single");
		source.subscribe(System.out::println);
	}
	
	private void code_2_20() {
		Observable<String> source = Observable.just("Hello Single");
		Single.fromObservable(source)
		.subscribe(System.out::println);
		
		Observable.just("Hello Single")
		.single("default item")
		.subscribe(System.out::println);
		
		String[] colors = {"Red", "Blue", "Green"};
		Observable.fromArray(colors)
		.first("default value")
		.subscribe(System.out::println);
		
		Observable.empty()
		.single("default item")
		.subscribe(System.out::println);
		
		Observable.just(new Order("ORD-1"), new Order("ORD-2"))
		.take(1)
		.single(new Order("default Order"))
		.subscribe(System.out::println);
	}
	
	private void code_2_22() {
		Single<String> source = Observable.just("Hello Single", "Error").single("default item");
		source.subscribe(System.out::println);
	}
	
	private void code_2_23() {
		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber 1 :: " + data));
		subject.onNext("1");
		subject.onNext("3");
		subject.subscribe(data -> System.out.println("Subscriber 2 :: " + data));
		subject.onNext("5");
		subject.onComplete();
	}
	
	private void code_2_24() {
		Float[] temperature = {10.1f, 13.4f, 12.5f};
		Observable<Float> source = Observable.fromArray(temperature);
		
		AsyncSubject<Float> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber 1 :: " + data));
		
		source.subscribe(subject);
	}
	
	public static Integer[] toIntegerArray(int[] intArray) {
		return IntStream.of(intArray).boxed().toArray(Integer[]::new);
	}
	
	public static void main(String args[]) {
		FirstExample exam = new FirstExample();
//		exam.emit();
//		exam.code_2_3();
//		exam.code_2_4();
//		exam.code_2_5();
//		exam.code_2_6();
//		exam.code_2_7();
//		exam.code_2_8();
//		exam.code_2_9();
//		exam.code_2_10();
//		exam.code_2_11();
//		exam.code_2_12();
//		exam.code_2_14();
//		exam.code_2_16();
//		exam.code_2_17();
//		exam.code_2_19();
//		exam.code_2_20();
//		exam.code_2_22();
//		exam.code_2_23();
		exam.code_2_24();
		
	}
	
}

