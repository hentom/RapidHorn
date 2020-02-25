func main()
{
  Int[] a;
  const Int[] b;
  Int[] c;

  const Int length;
  assume(0 <= length);
  assume(length < 3);

  Int i = 0;
  Int j = 0;
  while(i < length)
  {
    if (a[i] == b[i])
    {
      c[j] = i;
      j = j + 1;
    }
    i = i + 1;
  }
  
  Int pos = 0;
  while(pos < j)
  {
    assert(c[pos] <= pos + i - j);
    assert(pos <= c[pos]);
    pos = pos + 1;
  }
}
