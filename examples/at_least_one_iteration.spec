func main()
{
  const Int[] a;
  const Int alength;
  assume(alength >= 0);

  Int i = 0;
  Int j = 0;
  while(i < alength)
  {
    i = i + 1;
    j = 1;
  }

  if (alength == 0)
  {
    assert(j == 0);
  }
  else
  {
    assert(j == 1);
  }
}
