import { render, screen, fireEvent } from '@testing-library/react';
import Noteform from './components/Noteform';
import NoteList from './components/NoteList';
import NoteItem from './components/NoteItem';
import App from "./App";


test("adds note on submit", () => {
  const addNote = jest.fn();
  render(<Noteform addNote={addNote} />);

  fireEvent.change(screen.getByPlaceholderText(/enter note/i), {
    target: { value: "Test Note" }
  });

  fireEvent.click(screen.getByText(/add/i));

  expect(addNote).toHaveBeenCalledWith({
    title: "Test Note",
    status: "open"
  });
});
test("renders notes", () => {
  const notes = [
    { id: 1, title: "Note 1", status: "open" },
    { id: 2, title: "Note 2", status: "open" }
  ];
  render(<NoteList notes={notes} deleteNOTE={() => { }} />);
  expect(screen.getByText(/note 1/i)).toBeInTheDocument();
  expect(screen.getByText(/note 2/i)).toBeInTheDocument();
});

test("calls delete on button click", () => {
  const deleteNote = jest.fn();
  const note = { id: 1, text: "Test" };

  render(<NoteItem note={note} deleteNote={deleteNote} />);
  fireEvent.click(screen.getByText(/delete/i));
  expect(deleteNote).toHaveBeenCalledWith(1);
});

test("adds and deletes note", () => {
  render(<App />);

  fireEvent.change(screen.getByPlaceholderText(/enter note/i), {
    target: { value: "New Note" }
  });

  fireEvent.click(screen.getByText(/add/i));
  expect(screen.getByText(/new note/i)).toBeInTheDocument();
  fireEvent.click(screen.getByText(/delete/i));
  expect(screen.queryByText("New Note")).not.toBeInTheDocument();
});

test("does not add empty note", () => {
  render(<App />);
  fireEvent.click(screen.getByText(/add/i));

  expect(screen.queryByRole("listitem")).not.toBeInTheDocument();
});

test("clears input after adding note", () => {
  render(<App />);

  const input = screen.getByPlaceholderText(/enter note/i);
  fireEvent.change(input, {
    target: { value: "Test Note" }
  });
  fireEvent.click(screen.getByText(/add/i));

  expect(input.value).toBe("");
});

test("adds multiple notes correctly", () => {
  render(<App />);
  const input = screen.getByPlaceholderText(/enter note/i);
  fireEvent.change(input, {
    target: { value: "Note 1" }
  });
  fireEvent.click(screen.getByText(/add/i));

  fireEvent.change(input, {
    target: { value: "Note 2" }
  });
  fireEvent.click(screen.getByText(/add/i));
  expect(screen.getByText(/note 1/i)).toBeInTheDocument();
  expect(screen.getByText(/note 2/i)).toBeInTheDocument();
});
