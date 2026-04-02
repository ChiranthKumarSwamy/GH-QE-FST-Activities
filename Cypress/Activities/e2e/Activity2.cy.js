describe("Activity 2", () => {
  beforeEach(() => {
    cy.visit("http://localhost:3000");
  });

  it("adds a new todo", () => {
    cy.get('[data-cy="todo-input"]').type("Buy groceries");
    cy.get('[data-cy="add-button"]').click();

    // Verify the todo appears
    cy.get('[data-cy="todo-item"]').should("have.length", 1);
    cy.get('[data-cy="todo-text"]').should("contain", "Buy groceries");
  });

  it("verifies the todo appears in the list", () => {
    cy.get('[data-cy="todo-input"]').type("Walk the dog");
    cy.get('[data-cy="add-button"]').click();

    // Check it's visible
    cy.get('[data-cy="todo-item"]').should("be.visible");
    cy.contains("Walk the dog").should("exist");
  });

  it("marks a todo as complete", () => {
    // First add a todo
    cy.get('[data-cy="todo-input"]').type("Read a book");
    cy.get('[data-cy="add-button"]').click();

    // Then mark it complete
    cy.get('[data-cy="todo-checkbox"]').click();

    // Verify it's checked
    cy.get('[data-cy="todo-checkbox"]').should("be.checked");
  });

  it("verifies the checkbox is checked", () => {
    cy.get('[data-cy="todo-input"]').type("Finish homework");
    cy.get('[data-cy="add-button"]').click();

    cy.get('[data-cy="todo-checkbox"]').check();

    // Multiple ways to verify
    cy.get('[data-cy="todo-checkbox"]').should("be.checked");
    cy.get('[data-cy="todo-text"]').should("have.class", "line-through");
  });

  it("deletes a todo", () => {
    cy.get('[data-cy="todo-input"]').type("Clean room");
    cy.get('[data-cy="add-button"]').click();

    cy.get('[data-cy="delete-button"]').click();

    // Verify it's gone
    cy.get('[data-cy="todo-item"]').should("not.exist");
  });

  it("verifies todo is removed from the list", () => {
    // Add two todos
    cy.get('[data-cy="todo-input"]').type("First todo{enter}");
    cy.get('[data-cy="todo-input"]').type("Second todo{enter}");

    cy.get('[data-cy="todo-item"]').should("have.length", 2);

    // Delete the first one
    cy.get('[data-cy="delete-button"]').first().click();

    // Should only have one left
    cy.get('[data-cy="todo-item"]').should("have.length", 1);
    cy.contains("First todo").should("not.exist");
    cy.contains("Second todo").should("exist");
  });
});
