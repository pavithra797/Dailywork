import { render, screen } from '@testing-library/react';
import App from './App';

test('renders submit button', () => {
  render(<App />);
  const button = screen.getByText(/update/i); 
  expect(button).toBeInTheDocument();
});