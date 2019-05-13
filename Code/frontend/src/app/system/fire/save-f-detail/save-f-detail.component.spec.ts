import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveFDetailComponent } from './save-f-detail.component';

describe('SaveFDetailComponent', () => {
  let component: SaveFDetailComponent;
  let fixture: ComponentFixture<SaveFDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveFDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveFDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
