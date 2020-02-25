func main()
{
  const Int[] a;
  Int[] b;
  const Int alength;
  assume(0 <= alength);
  assume(alength < 10);

  Int i = 0;
  while(i < alength)
  {
    b[i] = a[i] + 1;
    i = i + 1;
  }
  
  Int j = 0;
  while(j < alength)
  {
    assert(a[j] == b[j] - 1);
    j = j + 1;
  }
}
