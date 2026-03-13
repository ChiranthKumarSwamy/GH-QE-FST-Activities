from selenium import webdriver
from selenium.webdriver.common.by import By

# Start the Driver
driver = webdriver.Firefox()

# Navigate to the URL
driver.get("https://training-support.net/")

# Print the title
print("Page title is:", driver.title)

# Click About Us
driver.find_element(By.LINK_TEXT, "About Us").click()

# Print new page title
print("New page title is:", driver.title)

# Keep browser open
input("Press Enter to close the browser...")

driver.quit()