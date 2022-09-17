import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Observer-Observable pattern is similar to Publish Subscriber pattern,
 * with the difference that in the former, there is a dependency between the observer and the observable
 * while there is a decoupling between subscriber and publisher in the latter
 */
public class ObservablePattern {

	public static abstract class Observable<T>
	{
		public abstract void subscribe ( Observer<T> observer );
		public abstract void unsubscribe ( Observer<T> observer );
		public abstract void subscribe(Observer<T> ...observersList);
		public abstract void produceValue();
	}
	
	public static abstract class Observer<T>
	{
		public Long id;
		public abstract void next(T value);
		public abstract void completed();
		public abstract void error();
	}
	
	
	public static class ObservableString extends Observable<String>
	{
		List<Observer<String>> subscribers = new ArrayList<>();
		
		@Override
		public void subscribe(Observer<String> observer) {
			subscribers.add(observer);
		}
		
		@Override
		public void subscribe(Observer<String> ...observersList)
		{
			subscribers.addAll(Arrays.asList(observersList));
		}
		

		@Override
		public void unsubscribe(Observer<String> observer) {
			List<Observer<String>> newSubscribers = new ArrayList<>();
			
			for(int i = 0; i< subscribers.size(); i++)
			{
				if(subscribers.get(i).id.equals(observer.id))
				{
					subscribers.get(i).completed();
				}
				else
				{
					newSubscribers.add(subscribers.get(i));
				}
			}
			subscribers = newSubscribers;
		}
		
		@Override
		public void produceValue()
		{
			String produced = ""+(Math.random()*100);
			for(int i = 0; i< subscribers.size(); i++)
			{
				subscribers.get(i).next(produced);
			}
		}
	}
	
	
	public static class StringObserver extends Observer<String>
	{

		public StringObserver(long id)
		{
			this.id = Long.valueOf(id);
		}
		
		@Override
		public void next(String value) {
			System.out.println("I am a string observer with id: "+this.id+", and I received: "+value);
		}

		@Override
		public void completed() {
			System.out.println("I am the string observer with id: "+this.id+", completed");
			
		}

		@Override
		public void error() {
			System.out.println("fuck you error");
		}
		
	}
	
	
	
	public static void main(String[] args)
	{
		Observable<String> observable = new ObservableString();
		Observer<String> obs1 = new StringObserver(1L);
		Observer<String> obs2 = new StringObserver(2L);
		Observer<String> obs3 = new StringObserver(3L);
		observable.subscribe(obs1,obs2,obs3);
		observable.produceValue();
	}
}
