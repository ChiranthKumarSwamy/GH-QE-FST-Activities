describe("Activity 3", () => {
  beforeEach(() => {
    cy.visit("http://localhost:3000");
  });

  it("completes full workflow: add, complete, delete todos", () => {
    // 1. Add 3 different todos
    cy.get('[data-cy="todo-input"]').type("Buy groceries{enter}");
    cy.get('[data-cy="todo-input"]').type("Walk the dog{enter}");
    cy.get('[data-cy="todo-input"]').type("Read a book{enter}");

    // Verify we have 3 todos
    cy.get('[data-cy="todo-item"]').should("have.length", 3);

    // 2. Mark the first one as complete
    cy.get('[data-cy="todo-checkbox"]').eq(0).click();
    cy.get('[data-cy="todo-checkbox"]').eq(0).should("be.checked");

    // 3. Mark the second one as complete
    cy.get('[data-cy="todo-checkbox"]').eq(1).click();
    cy.get('[data-cy="todo-checkbox"]').eq(1).should("be.checked");

    // 4. Delete the third one
    cy.get('[data-cy="delete-button"]').eq(2).click();

    // 5. Verify you have 2 todos remaining
    cy.get('[data-cy="todo-item"]').should("have.length", 2);

    // 6. Verify the count shows "Completed: 2"
    cy.get('[data-cy="todo-count"]')
      .should("contain", "Total: 2")
      .and("contain", "Completed: 2");
  });
});