import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerBookDetailsComponent } from './customer-book-details.component';

describe('CustomerBookDetailsComponent', () => {
  let component: CustomerBookDetailsComponent;
  let fixture: ComponentFixture<CustomerBookDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerBookDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerBookDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
