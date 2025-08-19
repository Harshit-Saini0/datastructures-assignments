import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody {

	Queue<Note> notes;

	public Melody(Queue<Note> song) {
		notes = song;
	}


	public double getTotalDuration() {
		Queue<Note> noteCopy = new LinkedList<Note>(notes);
		double duration = 0.0;
		boolean repeat = false;

		while(!noteCopy.isEmpty()) {
			Note note = noteCopy.poll();
			if(note.isRepeat()) {
				repeat = !repeat;
			}
			if(repeat || note.isRepeat()) {
				duration += note.getDuration();
			}
			duration += note.getDuration();
		}
		return duration;
	}

	public String toString() {
		Queue<Note> noteCopy = new LinkedList<Note>(notes);
		String str = "";

		while(!noteCopy.isEmpty()) {
			str += noteCopy.poll().toString() + "\n";
		}

		return str;
	}

	public void changeTempo(double tempo) {
		int size = notes.size();

		for(int i = 0; i < size; i++) {
			Note note = notes.poll();
			note.setDuration(note.getDuration() * tempo);
			notes.offer(note);
		}		
	}

	public void reverse() {
		Stack<Note> stack = new Stack<Note>();
		while(!notes.isEmpty()) {
			stack.push(notes.poll());
		}

		while(!stack.isEmpty()) {
			notes.offer(stack.pop());
		}
	}

	public void append(Melody other) {
		Queue<Note> otherCopy = new LinkedList<Note>(other.getNotes());
		while(!otherCopy.isEmpty()) {
			notes.offer(otherCopy.poll());
		}
	}

	public void play() {
		Queue<Note> noteCopy = new LinkedList<Note>(notes);
		boolean repeat = false;

		while(!noteCopy.isEmpty()) {
			Note note = noteCopy.poll();

			if(note.isRepeat() && !repeat) {

				repeat = !repeat;
				Queue<Note> repeatSection = new LinkedList<Note>();
				repeatSection.offer(note);
				note.play();

				while(repeat) {
					Note repeatNote = noteCopy.poll();
					repeatNote.play();
					repeatSection.offer(repeatNote);

					if(repeatNote.isRepeat()) {
						repeat = !repeat;
					}
				}

				while(!repeatSection.isEmpty()) {
					Note repeatNote = repeatSection.poll();
					repeatNote.play();
				}
			}
			
			else {
				note.play();
			}
		}

	}

	public Queue<Note> getNotes() {
		return notes;
	}
}
