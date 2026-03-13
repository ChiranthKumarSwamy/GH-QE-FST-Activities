# a = int(input("enter a number "))

# if a%2==0:
#     print("Even")
# else:
#     print("Odd")

num = int(input("Enter a number: "))
mod = num % 2
if mod > 0:
    print("You picked an odd number.")
else:
    print("You picked an even number.")