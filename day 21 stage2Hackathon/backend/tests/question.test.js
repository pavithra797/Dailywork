const request = require("supertest");
const app = require("../server");

describe("Questions API", () => {

    test("should fetch all questions", async () => {
        const res = await request(app).get("/questions");

        expect(res.statusCode).toBe(200);
        expect(Array.isArray(res.body)).toBe(true);
    });

    test("should save answers successfully", async () => {
        const data = [
            {
                question: "What is your pet's name?",
                answer: "Tommy",
                confirmAnswer: "Tommy"
            }
        ];
    });

    test("should return 404 for wrong route", async () => {
        const res = await request(app).get("/wrong-route");

        expect(res.statusCode).toBe(404);
    });

    test("response should be JSON", async () => {
        const res = await request(app).get("/questions");

        expect(res.headers["content-type"]).toMatch(/json/);
    });

    test("saved answers should be an array", async () => {
        const res = await request(app)
            .post("/questions/answers")
            .send([
                {
                    question: "what is your bestfriend name",
                    answer: "megha",
                    confirmAnswer: "megha"
                }
            ]);

        expect(res.statusCode).toBe(200);
    });

});

