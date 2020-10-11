package net.minecraft.server;

public class ControllerLook
{
  private EntityInsentient a;
  private float b;
  private float c;
  public boolean d;
  private double e;
  private double f;
  private double g;
  private boolean _allowInput = true;
  private boolean _resetPitch = true;

  public boolean isResetPitch()
  {
      return _resetPitch;
  }

  public void setResetPitch(boolean resetPitch)
  {
      _resetPitch = resetPitch;
  }

  public boolean isAllowInput()
  {
      return _allowInput;
  }

  public void setAllowInput(boolean allowInput)
  {
      _allowInput = allowInput;
  }

  public ControllerLook(EntityInsentient paramEntityInsentient)
  {
    this.a = paramEntityInsentient;
  }

  public void a(Entity paramEntity, float paramFloat1, float paramFloat2) {
    if (!isAllowInput())
        return;
    this.e = paramEntity.locX;
    if ((paramEntity instanceof EntityLiving))
      this.f = (paramEntity.locY + paramEntity.getHeadHeight());
    else {
      this.f = ((paramEntity.getBoundingBox().b + paramEntity.getBoundingBox().e) / 2.0D);
    }
    this.g = paramEntity.locZ;
    this.b = paramFloat1;
    this.c = paramFloat2;
    this.d = true;
  }

  public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
    if (!isAllowInput())
        return;
    this.e = paramDouble1;
    this.f = paramDouble2;
    this.g = paramDouble3;
    this.b = paramFloat1;
    this.c = paramFloat2;
    this.d = true;
  }

  public void a() {
    if (isResetPitch())
      this.a.pitch = 0.0F;

    if (this.d) {
      this.d = false;

      double d1 = this.e - this.a.locX;
      double d2 = this.f - (this.a.locY + this.a.getHeadHeight());
      double d3 = this.g - this.a.locZ;
      double d4 = MathHelper.sqrt(d1 * d1 + d3 * d3);

      float f1 = (float)(MathHelper.b(d3, d1) * 180.0D / 3.141592741012573D) - 90.0F;
      float f2 = (float)-(MathHelper.b(d2, d4) * 180.0D / 3.141592741012573D);
      this.a.pitch = a(this.a.pitch, f2, this.c);
      this.a.aK = a(this.a.aK, f1, this.b);
    } else {
      this.a.aK = a(this.a.aK, this.a.aI, 10.0F);
    }

    float f3 = MathHelper.g(this.a.aK - this.a.aI);

    if (!this.a.getNavigation().m())
    {
      if (f3 < -75.0F) {
       this.a.aK = (this.a.aI - 75.0F);
      }
      if (f3 > 75.0F)
        this.a.aK = (this.a.aI + 75.0F);
    }
  }

  private float a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f1 = MathHelper.g(paramFloat2 - paramFloat1);
    if (f1 > paramFloat3) {
      f1 = paramFloat3;
    }
    if (f1 < -paramFloat3) {
      f1 = -paramFloat3;
    }
    return paramFloat1 + f1;
  }

  public boolean b() {
    return this.d;
  }

  public double e()
  {
    return this.e;
  }

  public double f() {
    return this.f;
  }

  public double g() {
    return this.g;
  }
}