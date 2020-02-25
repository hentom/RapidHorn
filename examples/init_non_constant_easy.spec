func main()
{
  Int[] a;
  const Int alength;
  const Int v;
  assume(0 <= alength);
  assume(alength < 3);

  Int i = 0;
  while(i < alength)
  {
    a[i] = i + v;
    i = i + 1;
  }
  
  Int j = 0;
  while(j < alength)
  {
    assert(a[j] == j + v);
    j = j + 1;
  }
}
