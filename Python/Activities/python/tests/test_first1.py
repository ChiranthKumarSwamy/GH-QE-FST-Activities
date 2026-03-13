import pytest
import math


@pytest.mark.sqrt
def test_sqrt():
   num = 25
   assert math.sqrt(num) == 5

@pytest.mark.sqrt
def testsquare():
   num = 7
   assert num*num == 49

@pytest.mark.sqrt
def testquality():
   assert 10 == 10

@pytest.mark.sqrt
def test_add():
    assert 2 + 2 == 4

@pytest.mark.other
def test_subtract():
    assert 5 - 3 == 2