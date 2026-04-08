import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import Question from "../Questions";

const mockQuestions = [
    { questionId: 1, question: "What is your pet's name?" },
    { questionId: 2, question: "What is your favorite color?" }
];

const mockOnChange = jest.fn();

describe("Question Component", () => {

    test("should render dropdown", () => {
        render(
            <Question
                questions={mockQuestions}
                hideAnswers={false}
                onChange={mockOnChange}
            />
        );

        expect(screen.getByRole("combobox")).toBeInTheDocument();
    });

    test("should display questions in dropdown", () => {
        render(
            <Question
                questions={mockQuestions}
                hideAnswers={false}
                onChange={mockOnChange}
            />
        );

        expect(screen.getByText("What is your pet's name?")).toBeInTheDocument();
        expect(screen.getByText("What is your favorite color?")).toBeInTheDocument();
    });

    test("should select a question", async () => {
        render(
            <Question
                questions={mockQuestions}
                hideAnswers={false}
                onChange={mockOnChange}
            />
        );

        const select = screen.getByRole("combobox");

        await userEvent.selectOptions(select, "What is your pet's name?");

        expect(select.value).toBe("What is your pet's name?");
    });

    test("should type answer", async () => {
        render(
            <Question
                questions={mockQuestions}
                hideAnswers={false}
                onChange={mockOnChange}
            />
        );

        const input = screen.getByPlaceholderText("Answer");

        await userEvent.type(input, "hello");

        expect(input).toHaveValue("hello");
    });

    test("should show error when answer is less than 5 chars", async () => {
        render(
            <Question
                questions={mockQuestions}
                hideAnswers={false}
                onChange={mockOnChange}
            />
        );

        const input = screen.getByPlaceholderText("Answer");

        await userEvent.type(input, "123");

        expect(await screen.findByText("Min 5 chars")).toBeInTheDocument();
    });

});