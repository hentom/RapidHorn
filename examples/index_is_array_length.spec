func main()
{
  const Int alength;
  Int i = 0;

  while(i < alength)
  {
    i = i + 1;
  }

  assert(!(i < alength));

  assume(0 <= alength);
  assert(i == alength);
  }
