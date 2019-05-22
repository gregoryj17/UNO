import java.util.ArrayList;
import java.util.Random;

//A First-In, Random-Out data structure imported from my other repository, https://github.com/gregoryj17/Terrible-Data-Structures
public class FIRO<T> {
	private ArrayList<T> data;

	public FIRO(){
		data = new ArrayList<T>();
	}

	public FIRO(T t){
		data = new ArrayList<T>();
		data.add(t);
	}

	public FIRO(T... ts) {
		data = new ArrayList<T>();
		for(T t : ts){
			add(t);
		}
	}

	public void add(T t){
		data.add(t);
	}

	public void push(T t){
		add(t);
	}

	public T remove(){
		return data.remove((new Random()).nextInt(data.size()));
	}

	public T pop(){
		return remove();
	}

	public boolean isEmpty(){
		return data.isEmpty();
	}

}