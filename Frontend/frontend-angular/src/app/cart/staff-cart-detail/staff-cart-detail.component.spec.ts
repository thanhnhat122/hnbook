import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffCartDetailComponent } from './staff-cart-detail.component';

describe('StaffCartDetailComponent', () => {
  let component: StaffCartDetailComponent;
  let fixture: ComponentFixture<StaffCartDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffCartDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StaffCartDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
