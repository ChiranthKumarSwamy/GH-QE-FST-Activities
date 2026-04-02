describe("Activity #4 - Network Testing Practice", () => {

  beforeEach(() => {
    cy.visit("http://localhost:3000");
  });

  it("Intercept GET /api/todos and load fixture data", () => {
    cy.intercept("GET", "/api/todos", { fixture: "todos.json" }).as("getTodos");

    cy.visit("http://localhost:3000");
    cy.wait("@getTodos");

    cy.contains("Learn Cypress").should("be.visible");
    cy.contains("Write Tests").should("be.visible");
  });

  it("Intercept POST /api/todos and verify new todo is added", () => {
    cy.intercept("GET", "/api/todos", { body: [] }).as("getTodos");

    cy.intercept("POST", "/api/todos", (req) => {
      expect(req.body.title).to.eq("New Cypress Task");
      req.reply({
        statusCode: 201,
        body: {
          id: 3,
          title: "New Cypress Task",
          completed: false
        }
      });
    }).as("postTodo");

    cy.visit("http://localhost:3000");
    cy.wait("@getTodos");

    cy.get("input").first().type("New Cypress Task");
    cy.contains("button", /add|submit|create/i).click();

    cy.wait("@postTodo");
  });

  it("Test error scenario - POST returns 500", () => {
    cy.intercept("GET", "/api/todos", { body: [] }).as("getTodos");

    cy.intercept("POST", "/api/todos", {
      statusCode: 500,
      body: {
        error: "Server Error"
      }
    }).as("postError");

    cy.visit("http://localhost:3000");
    cy.wait("@getTodos");

    cy.get("input").first().type("Fail Task");
    cy.contains("button", /add|submit|create/i).click();

    cy.wait("@postError");
    cy.contains(/server error|failed|something went wrong/i).should("be.visible");
  });

});